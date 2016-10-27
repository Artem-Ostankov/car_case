import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._
import play.api.Logger


/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {

    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }

  }

  "HomeController" should {

    "render the index page" in {
      val home = route(app, FakeRequest(GET, "/")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("All Cars")
    }

  }

  "Check index" should {

    "return json list" in {
      // quick stab, in reality a full communication flow check should be implemented
      contentAsString(route(app, FakeRequest(GET, "/cars")).get) must startWith ("[")
      contentAsString(route(app, FakeRequest(GET, "/cars")).get) must endWith ("]")
    }

  }

}
