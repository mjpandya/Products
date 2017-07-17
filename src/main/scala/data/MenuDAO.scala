package data

import model.{ShopByCategoryMenu, HeaderMenu}
import org.bson.types.ObjectId
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.conversions.scala.{RegisterConversionHelpers, RegisterJodaLocalDateTimeConversionHelpers, RegisterJodaTimeConversionHelpers}

/**
 * Created by 787655 on 7/11/2017.
 */
object MenuDAO {
  implicit var header_menu_collection = MongoProxy.header_menu_collection()
  implicit var category_menu_collection = MongoProxy.category_menu_collection()
  object HeaderMenuDao extends SalatDAO[HeaderMenu, ObjectId](collection = header_menu_collection)
  object CategoryMenuDao extends SalatDAO[ShopByCategoryMenu, ObjectId](collection = category_menu_collection)

  def getHeaderMenu() = {
    println("Actual Call to Mongo DB")
    HeaderMenuDao.find(MongoDBObject.empty)
  }
  def getShopByCategoryMenu()={
    CategoryMenuDao.find(MongoDBObject.empty)
  }
}
