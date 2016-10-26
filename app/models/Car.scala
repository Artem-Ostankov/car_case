package models

import play.api.libs.json.Json

/**
  * Created by arieel on 26.10.16.
  */
case class Car (title: String, fuel: String, price: Int, new_car: Boolean ) {
}

object Car {

  implicit val carFormat = Json.format[Car]
}
