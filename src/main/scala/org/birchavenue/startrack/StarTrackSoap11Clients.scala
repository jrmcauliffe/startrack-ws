package org.birchavenue.startrack

import scalaxb._

import org.scala_tools.time.Imports._
import org.joda.time.format._

trait StarTrackSoap11Clients extends Soap11Clients {
  this: scalaxb.HttpClients =>
  val u: String
  val p: String
  override lazy val soapClient: Soap11Client = new StarTrackSoap11Client {}

  trait StarTrackSoap11Client extends Soap11Client {

    def date = DateTime.now
    def nonce = new sun.misc.BASE64Encoder().encode(DateTime.now.getMillis().toString.getBytes("UTF-8"))
    def wssecurity = <wsse:Security soap11:mustUnderstand="1" xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
                       <wsse:UsernameToken wsu:Id="UsernameToken-1" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd">
                         <wsse:Username>{ u }</wsse:Username>
                         <wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">{ p }</wsse:Password>
                         <wsse:Nonce EncodingType="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary">{ nonce }</wsse:Nonce>
                         <wsu:Created>{ date }</wsu:Created>
                       </wsse:UsernameToken>
                     </wsse:Security>

    override def requestResponse(body: scala.xml.NodeSeq, headers: scala.xml.NodeSeq,
      scope: scala.xml.NamespaceBinding, address: java.net.URI,
      webMethod: String, action: Option[java.net.URI]) =
      super.requestResponse(body, headers.toSeq ++ wssecurity, scope, address, webMethod, action)
  }
}