package models

import play.api.libs.json.Json

/**
  * Created by arieel on 26.10.16.
  */
case class Person (name: String)

object Person {

  implicit val personFormat = Json.format[Person]
}
