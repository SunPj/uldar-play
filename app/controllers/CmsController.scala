package controllers

import com.github.sunpj.uldar.extension.{ApiCallRequest, ExtensionService, SystemError, UnsupportedRequest, Forbidden => UForbidden, InvalidRequest => UInvalidRequest, NotFound => UNotFound, Ok => UOk}
import javax.inject._
import models.User
import play.api.Logging
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext

/**
 * Uldar CMS integration controller
 */
@Singleton
class CmsController @Inject()(extensionService: ExtensionService[User],
                              val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext) extends BaseController with Logging {

  /**
   * Maps http calls to extension handlers and returns the result
   */
  def cmsAction(path: String) = Action(parse.json).async { implicit request =>
    // Use any authorization/authentication library to retrieve current user identity
    val user = User("root@sunsongs.ru")

    val req = ApiCallRequest(request.body, Some(user))

    // Mapping calls to extension handlers
    extensionService.processApiCall(path.split("/").toIndexedSeq, req) map {
      case r: UOk => Ok(r())
      case UForbidden => Forbidden
      case UInvalidRequest(errors) => BadRequest(Json.toJson(errors))
      case UNotFound => NotFound
      case UnsupportedRequest => NotImplemented
      case SystemError => InternalServerError
    }
  }
}
