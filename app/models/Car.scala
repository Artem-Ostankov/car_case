package models

import play.api.libs.json.Json

/**
  * Created by arieel on 26.10.16.
  */
case class Car (uuid: String, title: String, fuel: String, price: Int, new_car: Boolean ) {
}

object Car {

  implicit val carFormat = Json.format[Car]
//  implicit val carFormat = (
//      (__ \ "id").read[Long] and
//      (__ \ "title").read[String] and
//      (__ \ "fuel").read[String] and
//      (__ \ "price").read[Int] and
//      (__ \ "new_car").read[Boolean]
//    )(Car)
}
