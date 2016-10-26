package controllers

import javax.inject._

import models.{DB, Person, Car}
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Hi Bro !"))
  }

//  val personForm: Form[Person] = Form {
//    mapping(
//      "name" -> text
//    )(Person.apply)(Person.unapply)
//  }
//
//  def addPerson = Action { implicit request =>
//    val person = personForm.bindFromRequest.get
//    DB.save(person)
//    Redirect(routes.HomeController.index())
//  }
//
//  def getPersons = Action {
//    val persons = DB.query[Person].fetch()
//    Ok(Json.toJson(persons))
//  }


  val carForm: Form[Car] = Form {
    mapping(
      "title" -> text,
      "fuel" -> text,
      "price" -> number,
      "newcar" -> boolean
    )(Car.apply)(Car.unapply)
  }

  def addCar = Action { implicit request =>
    println(carForm)
    println(carForm.bindFromRequest)
    val car = carForm.bindFromRequest.get
    DB.save(car)
    Redirect(routes.HomeController.index())
  }

  def getCars = Action {
    val cars = DB.query[Car].fetch()
    Ok(Json.toJson(cars))
  }


}
