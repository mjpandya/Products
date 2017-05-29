package model

import spray.json.DefaultJsonProtocol

/**
 * Created by 787655 on 5/29/2017.
 */

case class ProductDetails(name:String,imagePath:String,screen:String,screenType:String,ram:String,rom:String,androidVersion:String,price:String)

object ProductsProtocol extends DefaultJsonProtocol{
  implicit val productDetails_format = jsonFormat8(ProductDetails.apply)
}