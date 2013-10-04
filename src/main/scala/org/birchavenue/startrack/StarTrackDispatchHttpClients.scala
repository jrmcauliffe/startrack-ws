package org.birchavenue.startrack

import scalaxb._
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dispatch._, Defaults._

trait StarTrackDispatchHttpClients extends HttpClients {

  val logger = LoggerFactory.getLogger("org.birchavenue.startrack");
  val httpClient = new StarTrackDispatchHttpClient {}

  trait StarTrackDispatchHttpClient extends HttpClient {
    

    def request(in: String, address: java.net.URI, headers: Map[String, String]): String = {
      logger.debug("REQUEST: " + in)
      val req = url(address.toString) << in <:< headers
      val s = Http(req)
      val res = s()
      logger.debug("RESPONSE: " + res.getResponseBody())
      res.getResponseBody()
    }
  }
}
