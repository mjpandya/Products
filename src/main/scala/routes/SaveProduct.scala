package routes
import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes}
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.util.Timeout
import config.AppConfig
import model.ProductDetails

import scala.util.{Failure, Success}

/**
 * Created by 787655 on 5/29/2017.
 */
trait SaveProduct extends AppConfig{
  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
  import model.ProductsProtocol._
  import spray.json._
  def saveProduct(system:ActorSystem) = {
    val productProcessort = system.actorSelection("/*/ProductProcessor")
    logRequestResult("Save Product into Product List", akka.event.Logging.InfoLevel){
      put{
          entity(as[ProductDetails]) { product =>
            implicit val timeout = Timeout(300, TimeUnit.SECONDS)
            val res = (productProcessort ? product).mapTo[ProductDetails]
            onComplete(res) {
              case Success(response) =>
                complete(HttpResponse(entity = HttpEntity(MediaTypes.`application/json`, response.toJson.toString)))
              case Failure(error) =>
                complete(error)
            }
          }
      }
    }
  }
}
