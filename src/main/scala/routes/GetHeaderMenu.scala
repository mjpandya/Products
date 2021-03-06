package routes

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{HttpEntity, MediaTypes, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.util.Timeout
import config.AppConfig
import data.MenuDAO
import model.{ShopByCategoryMenu, HeaderMenu}
import akka.pattern.ask

import scala.util.{Failure, Success}

/**
 * Created by 787655 on 7/11/2017.
 */
trait GetHeaderMenu extends AppConfig {

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
  import spray.json._

  def getDisplayHeader(system: ActorSystem) = {
    val menuProcessor = system.actorSelection("/*/MenuProcessor")
    path("gettopheader") {
      println("Recieved Request.....")
      logRequestResult("Get Header Menu from DB", akka.event.Logging.InfoLevel) {
        get {
          import model.ProductsProtocol._
          println("Executing GET for Header...")
          implicit val timeout = Timeout(300, TimeUnit.SECONDS)
          //val response = (menuProcessor ? "HEADER_MENU").mapTo[HeaderMenu]
          val menuList = MenuDAO.getHeaderMenu()
          if (!menuList.isEmpty) {
            var res: Seq[HeaderMenu] = Nil
            for (menu <- menuList) {
              val response = HeaderMenu(menu.MenuId,menu.ProductName,menu.LatestModels)
              res = res :+ response
            }
            complete(HttpResponse(entity = HttpEntity(MediaTypes.`application/json`, res.toJson.toString)))
          }else{
            complete(HttpResponse(entity = HttpEntity(MediaTypes.`application/json`, "ERROR".toJson.toString)))
          }

          /* onComplete(response) {
                case Success(response) =>
                  println("Response Recived : " + response.toJson.toString)
                  complete(HttpResponse(entity = HttpEntity(MediaTypes.`application/json`, response.toJson.toString)))
                case Failure(error) =>
                  println("Error : " + error.getMessage)
                  complete(error)
              }*/
          //}
        }
      }
    }
  }
  def getCategoryHeader(system:ActorSystem) = {
    val menuProcessor = system.actorSelection("/*/MenuProcessor")
    path("getshopbycategorymenu") {
      println("Recieved Request.....")
      logRequestResult("Get Header Menu from DB", akka.event.Logging.InfoLevel) {
        get {
          import model.ProductsProtocol._
          println("Executing GET for Header...")
          implicit val timeout = Timeout(300, TimeUnit.SECONDS)
          //val response = (menuProcessor ? "HEADER_MENU").mapTo[HeaderMenu]
          val menuList = MenuDAO.getShopByCategoryMenu()
          if (!menuList.isEmpty) {
            var res: Seq[ShopByCategoryMenu] = Nil
            for (menu <- menuList) {
              val response = ShopByCategoryMenu(menu.MenuId,menu.Category,menu.SubCategory)
              res = res :+ response
            }
            complete(HttpResponse(entity = HttpEntity(MediaTypes.`application/json`, res.toJson.toString)))
          }else{
            complete(HttpResponse(entity = HttpEntity(MediaTypes.`application/json`, "ERROR".toJson.toString)))
          }
        }
      }
    }
  }
}