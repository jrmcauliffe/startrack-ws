package scalaxb

case class Soap11Fault[+A](original: Any, detail: Option[A], headers: scala.xml.NodeSeq) {
  def asFault[B: scalaxb.XMLFormat] = Soap11Fault(original, detail map {
    case x: soapenvelope11.Detail => x.any.head.value match {
      case node: scala.xml.Node => scalaxb.fromXML[B](node)
      case _ => sys.error("unsupported fault: " + toString)
    }
    case _ => sys.error("unsupported fault: " + toString)
  }, headers)
}

trait Soap11Clients { this: HttpClients =>
  lazy val soapClient: Soap11Client = new Soap11Client {}
  def baseAddress: java.net.URI

  trait Soap11Client {
    import soapenvelope11._
    val SOAP_ENVELOPE11_URI = "http://schemas.xmlsoap.org/soap/envelope/"

    def soapRequest(in: Option[Envelope], scope: scala.xml.NamespaceBinding,
                    address: java.net.URI, webMethod: String, action: Option[java.net.URI]): Envelope = {
      val merged = scalaxb.toScope(((Some("soap11") -> SOAP_ENVELOPE11_URI) ::
        scalaxb.fromScope(scope)).distinct: _*)
      val r = in map  { scalaxb.toXML(_, Some(SOAP_ENVELOPE11_URI), Some("Envelope"), merged) match {
        case elem: scala.xml.Elem => elem
        case x => sys.error("unexpected non-elem: " + x.toString)
      }}
      val headers = scala.collection.mutable.Map[String, String]("Content-Type" -> "text/xml; charset=utf-8") ++
        (action map { x => "SOAPAction" -> """"%s"""".format(x)})

      val s = httpClient.request(r map {_.toString} getOrElse {""}, address, headers.toMap)

      try {
        val response = scala.xml.XML.loadString(s)
        scalaxb.fromXML[Envelope](response)
      }
      catch {
        case e: Exception => sys.error(e.toString + ": " + s)
      }
    }

    def requestResponse(body: scala.xml.NodeSeq, headers: scala.xml.NodeSeq, scope: scala.xml.NamespaceBinding,
                        address: java.net.URI, webMethod: String, action: Option[java.net.URI]):
        Either[Soap11Fault[Detail], (scala.xml.NodeSeq, scala.xml.NodeSeq)] = {
      val bodyRecords = body.toSeq map { DataRecord(None, None, _) }
      val headerOption = headers.toSeq.headOption map { _ =>
        Header(headers.toSeq map {DataRecord(None, None, _)}, Map())
      }
      val envelope = Envelope(headerOption, Body(bodyRecords, Map()), Nil, Map())
      buildResponse(soapRequest(Some(envelope), scope, address, webMethod, action))
    }

    def soapResponse(location: Option[String], params: Map[String, Any],
                     address: java.net.URI, webMethod: String, action: Option[java.net.URI]):
        Either[Soap11Fault[Detail], (scala.xml.NodeSeq, scala.xml.NodeSeq)] = {
      buildResponse(soapRequest(None, scala.xml.TopScope, address, webMethod, action))
    }

    def buildResponse(soapResponse: Envelope):
        Either[Soap11Fault[Detail], (scala.xml.NodeSeq, scala.xml.NodeSeq)] = {
      val header: scala.xml.NodeSeq =
        soapResponse.Header.toSeq flatMap { header =>
          header.any collect {
            case DataRecord(_, _, x: scala.xml.Node) => x
          }
        }
      soapResponse.Body.any.headOption match {
        case Some(DataRecord(_, _, x: scala.xml.Elem)) if (x.label == "Fault") &&
            (x.scope.getURI(x.prefix) == SOAP_ENVELOPE11_URI) =>
          val fault = scalaxb.fromXML[soapenvelope11.Fault](x)
          Left(Soap11Fault(fault, fault.detail, header))
        case _ =>
          Right(header, soapResponse.Body.any collect {
            case DataRecord(_, _, x: scala.xml.Node) => x
          })
      }
    }
  }
}
