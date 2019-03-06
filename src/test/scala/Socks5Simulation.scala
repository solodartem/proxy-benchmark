import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Socks5Simulation extends Simulation {

  val socks5 = http
    .baseUrl("http://google.com") // Here is the root for all relative URLs
    .proxy(Proxy("localhost", 1081).socks5.credentials("test", "test"))
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
//    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
//    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")


  val scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses
    .exec(http("request_1")
    .get("/"));

  setUp(scn.inject(atOnceUsers(1)).protocols(socks5))
}
