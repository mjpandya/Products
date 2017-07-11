package data

import model.{HeaderMenu}
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
  implicit var menu_collection = MongoProxy.menu_collection()
  object MenuDao extends SalatDAO[HeaderMenu, ObjectId](collection = menu_collection)
  def getHeaderMenu() = {
    println("Actual Call to Mongo DB")
    MenuDao.find(MongoDBObject.empty)
    /*println("Total Row :" + menuList.toArray.size)
      val menus = menuList.toArray
     for(menu <- menus){
       println("Menu :" + menu)
     }*/
  }
}
