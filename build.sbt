import _root_.sbtassembly.Plugin.AssemblyKeys._
import _root_.sbtassembly.Plugin.MergeStrategy
import _root_.sbtassembly.Plugin._

name := "Products"

version := "1.0"

scalaVersion := "2.11.6"

resolvers += "spray repo" at "http://repo.spray.io"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers += "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases"

resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-client" % sprayV ,
    "io.spray"            %% "spray-caching" % sprayV ,
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "io.spray"            %%  "spray-testkit" % "1.3.3" % "test",
    "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test"
  )
}

libraryDependencies += "com.typesafe.akka" % "akka-http-spray-json-experimental_2.11" % "2.4.7"

libraryDependencies += "org.scalaj" % "scalaj-http_2.10" % "2.3.0"

libraryDependencies += "io.spray" %%  "spray-json" % "1.3.2"

libraryDependencies += "io.spray" % "spray-httpx_2.11" % "1.3.3"

libraryDependencies += "com.typesafe.akka" %% "akka-http-core" % "2.4.7"

libraryDependencies += "com.typesafe.akka" %% "akka-http-experimental" % "2.4.7"

// https://mvnrepository.com/artifact/com.novemberain/quartz-mongodb
//libraryDependencies += "com.novemberain" % "quartz-mongodb" % "2.0.0-rc3"

libraryDependencies += "org.mongodb" % "casbah-commons_2.11" % "2.8.1"

// https://mvnrepository.com/artifact/org.mongodb/casbah-commons_2.11
//libraryDependencies += "org.mongodb" % "casbah-commons_2.11" % "3.1.1" exclude("org.mongodb","mongo-java-driver")


libraryDependencies += "com.novus" %% "salat" % "1.9.9"

libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.3.9"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

libraryDependencies += "commons-codec" % "commons-codec" % "1.6"

libraryDependencies += "com.gettyimages" %% "spray-swagger" % "0.5.1"

//htps://mvnrepository.com/artifact/com.enragedginger/akka-quartz-scheduler_2.11
libraryDependencies += "com.enragedginger" % "akka-quartz-scheduler_2.11" % "1.6.0-akka-2.4.x"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka_2.11
//libraryDependencies += "org.apache.kafka" % "kafka_2.11" % "0.10.2.0"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
//libraryDependencies += "org.apache.kafka" % "kafka-clients" % "0.10.2.0"

libraryDependencies += "org.apache.kafka" % "kafka_2.11" % "0.8.2.1" exclude("org.slf4j", "slf4j-log4j12")

// https://mvnrepository.com/artifact/org.apache.kafka/kafka_2.11
//libraryDependencies += "org.apache.kafka" % "kafka_2.11" % "0.9.0.1" exclude("org.slf4j", "slf4j-log4j12")

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.3.9" % "test"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.3.9"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

libraryDependencies += "org.mockito" % "mockito-all" % "1.9.5"

libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0"

assemblySettings

mainClass in assembly := Some("Products")

excludedJars in assembly := {
  val cp = (fullClasspath in assembly).value
  cp filter {_.data.getName == "shapeless_2.11-1.2.4.jar"}
}

test in assembly := {}

mergeStrategy in assembly := {
  case x if x.contains("StaticLoggerBinder") => MergeStrategy.first
  case x if x.contains("StaticMDCBinder") => MergeStrategy.first
  case x if x.contains("StaticMarkerBinder") => MergeStrategy.first
  case x if x.contains("logback-classic-1.1.2.jar") => MergeStrategy.first
  case x =>
    val oldStrategy = (mergeStrategy in assembly).value
    oldStrategy(x)
}

fork in Test := true