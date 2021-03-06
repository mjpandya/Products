import akka.actor.{Props, ActorSystem}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.server.Directives._
import akka.routing.RoundRobinPool
import akka.stream.ActorMaterializer
import config.AppConfig
import routes.{GetHeaderMenu, SaveProduct}
import utils.CorsSupport
/**
 * Created by 787655 on 5/29/2017.
 */
object Products extends App with AppConfig with CorsSupport with SaveProduct with GetHeaderMenu{
  implicit val system = ActorSystem("products")
  implicit val materializer = ActorMaterializer()
  val poolSize = config.getInt("poolSize")


  val productProccessor = system.actorOf(RoundRobinPool(poolSize).props(Props[processor.ProductProcessor]), "ProductProcessor")
  val menuProccessor = system.actorOf(RoundRobinPool(poolSize).props(Props[processor.MenuProcessor]), "MenuProcessor")

  val corsHeaders = List(RawHeader("Access-Control-Allow-Origin", "*"),
    RawHeader("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS, DELETE"),
    RawHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization") )

  def route = {
    respondWithHeaders(corsHeaders){getDisplayHeader(system) ~ getCategoryHeader(system) ~ saveProduct(system)}
  }
  val loggingRoute =  logRequestResult("products" , akka.event.Logging.InfoLevel)(route)
  Http().bindAndHandle(loggingRoute, host, port)
}
