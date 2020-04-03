package cms

import com.github.sunpj.uldar.extension.{ApiExtension, Ok}
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

class SimpleMathApiExtension[U] @Inject()()(implicit ec: ExecutionContext) extends ApiExtension[U] {
  override def name: String = "math"

  override def apiCallHandler: PartialFunction[Seq[String], ApiCall] = {
    case Seq("sum") => sum
    case Seq("product") => product
  }

  /**
    * @return sum of given numbers
    */
  private def sum: ApiCall = ApiCall[Array[Int]] { request =>
    Future.successful {
      Ok(request.data.sum)
    }
  }

  /**
    * @return product of given numbers
    */
  private def product: ApiCall = ApiCall[Array[Int]] { request =>
    Future.successful {
      Ok(request.data.product)
    }
  }
}
