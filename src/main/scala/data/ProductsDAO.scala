package data

import com.mongodb.WriteConcern
import com.mongodb.casbah.Imports._
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._
import model.{SmartPhone}


/**
 * Created by 787655 on 5/29/2017.
 */
object ProductsDAO {
  implicit var products_collection = MongoProxy.products_collection()
  object ProductDao extends SalatDAO[SmartPhone, ObjectId](collection = products_collection)

  def saveProduct(pd : SmartPhone) = {
    val productId  = ProductDao.insert(pd ,new WriteConcern()).get
    ProductDao.findOne(MongoDBObject("_id" ->  productId)).get
  }
}
