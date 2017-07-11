package data

import com.mongodb.WriteConcern
import com.mongodb.casbah.Imports._
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._
import model.{SmartPhone}
import org.bson.types.ObjectId
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.conversions.scala.{RegisterConversionHelpers, RegisterJodaLocalDateTimeConversionHelpers, RegisterJodaTimeConversionHelpers}


/**
 * Created by 787655 on 5/29/2017.
 */
object ProductsDAO {
  implicit var products_collection = MongoProxy.products_collection()
  object ProductDao extends SalatDAO[SmartPhone, ObjectId](collection = products_collection)

  def saveProduct(pd : SmartPhone) = {
    val productId  = ProductDao.insert(pd).get
    ProductDao.findOne(MongoDBObject("_id" ->  productId)).get
  }
  def findProduct(model:String) = {
    ProductDao.findOne(MongoDBObject("Model" -> model)).get
  }
  def findProductByPrice(priceFrom:String,priceTo:String) ={
    ProductDao.collection.find($and ({"Price" $gte priceFrom.toDouble},{"Price" $lte priceTo.toDouble}))
  }
  def findProductByFilter(filter:Array[String]) = {
    ProductDao.collection.find()
  }
}
