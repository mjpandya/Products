package config

import com.typesafe.config.ConfigFactory

/**
 * Created by 787655 on 5/29/2017.
 */
trait AppConfig {
  val config = ConfigFactory.load

  def host = config.getString("Env.host")
  def port = config.getInt("Env.port")
}
