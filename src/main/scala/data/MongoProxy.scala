package data

import com.mongodb.casbah.{MongoClient, MongoClientURI}
import com.typesafe.config.ConfigFactory

/**
 * Created by mchouk200 on 6/27/14.
 */
object MongoProxy {
  val config = ConfigFactory.load
  //def wl_collection =  {MongoClient(MongoClientURI(config.getString("Mongo.uri")))(config.getString("Mongo.dbName"))(config.getString("Mongo.waitlistCollection"))}
  def products_collection(mongoURI:String=config.getString("Mongo.uri"),dbName:String=config.getString("Mongo.dbName"),collection:String=config.getString("Mongo.productsCollection")) =  {
    MongoClient(MongoClientURI(mongoURI))(dbName)(collection)
  }
  def menu_collection(mongoURI:String=config.getString("Mongo.uri"),dbName:String=config.getString("Mongo.dbName"),collection:String=config.getString("Mongo.menuCollection")) =  {
    MongoClient(MongoClientURI(mongoURI))(dbName)(collection)
  }
}