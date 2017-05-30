package model

import org.bson.types.ObjectId
import spray.json.DefaultJsonProtocol

/**
 * Created by 787655 on 5/29/2017.
 */
case class SimDetails(SimType:String,SimSize:String)
case class SimData(DualSim:Boolean,SimOne:SimDetails,SimTwo:SimDetails)
case class Design(Dimensions:String,Weight:String)
case class Display(DisplayType:String,Touch:String,Size:String,Pixels:String,PPI:String,Features:String)
case class Memory(Ram:String,Internal:String,ExternalSupport:String,CardSlotType:String)
case class Connectivity(GPRS:String,EDGE:String,THREEG:String,FOURG:String,VoLTE:String,WIFI:String,Bluetooth:String,USB:String)
case class CameraDetails(Pixels:String,AutoFocus:String,Features:String,VidoeRecording:String,Flash:String)
case class CameraData(RearCamera:CameraDetails,FrontCamera:CameraDetails)
case class Technical(OS:String,CPU:String,GPU:String,Browser:String)
case class Multimedia(Supports:String,Email:String,Music:String,Video:String,FMRadio:String,DocumentReader:String)
case class Extra(GPS:String,FingerprintSensor:String,Sensors:String,HeadPhoneJack:String)
case class Bettery(Type:String,Size:String)
case class SmartPhone(BrandName:String,ImagePath:String,SimData:SimData,Design:Design,Display:Display,Memory:Memory,Connectivity:Connectivity,Camera:CameraData,Technical:Technical,
                      Multimedia:Multimedia,Extra:Extra,Bettery:Bettery,ReviewLink:String)

object ProductsProtocol extends DefaultJsonProtocol{
  implicit val simDetails_format = jsonFormat2(SimDetails.apply)
  implicit val simData_format = jsonFormat3(SimData.apply)
  implicit val design_format = jsonFormat2(Design.apply)
  implicit val display_format = jsonFormat6(Display.apply)
  implicit val memory_format = jsonFormat4(Memory.apply)
  implicit val connectivity_format = jsonFormat8(Connectivity.apply)
  implicit val cameraDetails_format = jsonFormat5(CameraDetails.apply)
  implicit val cameraData_format= jsonFormat2(CameraData.apply)
  implicit val technical_format = jsonFormat4(Technical.apply)
  implicit val multimedia_format = jsonFormat6(Multimedia.apply)
  implicit val extra_format = jsonFormat4(Extra.apply)
  implicit val bettery_format = jsonFormat2(Bettery.apply)
  implicit val productDetails_format = jsonFormat13(SmartPhone.apply)

}