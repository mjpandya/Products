import akka.actor.{Props, ActorSystem}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.routing.RoundRobinPool
import akka.stream.ActorMaterializer
import config.AppConfig
import routes.SaveProduct

/**
 * Created by 787655 on 5/29/2017.
 */
object Products extends App with AppConfig with SaveProduct{
  implicit val system = ActorSystem("products")
  implicit val materializer = ActorMaterializer()
  val poolSize = config.getInt("poolSize")

  val productProccessor = system.actorOf(RoundRobinPool(poolSize).props(Props[processor.ProductProcessor]), "ProductProcessor")
  def route = {
    saveProduct(system)
  }
  val loggingRoute =  logRequestResult("products" , akka.event.Logging.InfoLevel)(route)
  Http().bindAndHandle(loggingRoute, host, port)
}
