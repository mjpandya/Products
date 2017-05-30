package processor

import java.util.concurrent.TimeUnit

import akka.actor.Actor
import akka.util.Timeout
import config.AppConfig
import data.ProductsDAO
import model.{SmartPhone}

/**
 * Created by 787655 on 5/29/2017.
 */
class ProductProcessor extends Actor with AppConfig{
  implicit val timeout = Timeout(100, TimeUnit.SECONDS)
  def receive = {
    case product:SmartPhone => {
      val savedProduct = ProductsDAO.saveProduct(product)
      sender() ! savedProduct.copy()
    }
  }
}
