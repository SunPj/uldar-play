package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.{JsNumber, Json}
import play.api.libs.ws.WSClient
import play.api.test.Helpers._

/**
 * CmsControllerSpec
 */
class CmsControllerSpec extends PlaySpec with GuiceOneServerPerSuite {

  val wsClient = app.injector.instanceOf[WSClient]
  val myPublicAddress = s"localhost:$port"

  "CmsController" should {

    "return the sum of numbers" in {
      val sumApiExtensionUrl = s"http://$myPublicAddress/cms/math/sum"
      // The test payment gateway requires a callback to this server before it returns a result...
      val callbackURL = s"http://$myPublicAddress/callback"
      // await is from play.api.test.FutureAwaits
      val response = await(wsClient.url(sumApiExtensionUrl).post(Json.arr(1, 2, 3)))

      response.status mustBe OK
      response.contentType mustBe "application/json"
      response.json mustBe JsNumber(6)
    }

    "return the product of numbers" in {
      val sumApiExtensionUrl = s"http://$myPublicAddress/cms/math/product"
      // The test payment gateway requires a callback to this server before it returns a result...
      val callbackURL = s"http://$myPublicAddress/callback"
      // await is from play.api.test.FutureAwaits
      val response = await(wsClient.url(sumApiExtensionUrl).post(Json.arr(3, 5, 10)))

      response.status mustBe OK
      response.contentType mustBe "application/json"
      response.json mustBe JsNumber(150)
    }
  }
}
