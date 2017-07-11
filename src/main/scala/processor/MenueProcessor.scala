package processor

import java.util.concurrent.TimeUnit

import akka.actor.Actor
import akka.util.Timeout
import config.AppConfig
import data.MenuDAO
import model.HeaderMenu

/**
 * Created by 787655 on 7/11/2017.
 */
class MenuProcessor extends Actor with AppConfig{
  implicit val timeout = Timeout(100, TimeUnit.SECONDS)
  def receive = {
    case "HEADER_MENU" => {
       println("Getting DATA from DB.")
        MenuDAO.getHeaderMenu()
    }
 }
}
