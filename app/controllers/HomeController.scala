package controllers

import javax.inject._

import models.{DB, Car}
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

  var bebug = true

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index())
  }

  val carForm = Form {
    tuple(
      "title" -> text,
      "fuel" -> text,
      "price" -> number,
      "newcar" -> boolean
    )
  }

  def addCar = Action { implicit request =>
    if (bebug) {
      println(carForm)
      println(carForm.bindFromRequest)
    }

    val carFormRequest = carForm.bindFromRequest.get

    // This is a bit of a hack :(
    // I couldn't find how to either extract auto generated id from sorm,
    // either how to set UUID inside the mapping above

    val car = new Car(
      java.util.UUID.randomUUID.toString,
      carFormRequest._1,
      carFormRequest._2,
      carFormRequest._3,
      carFormRequest._4
    )

    DB.save(car)
    Redirect(routes.HomeController.index())
  }

  def getCars = Action {
    val cars = DB.query[Car].fetch()
    if (bebug) {
      println(cars)
      println(Json.toJson(cars))
    }
    Ok(Json.toJson(cars))
  }


  def newCar = Action {
    Ok(views.html.new_car())
  }

  def oneCar = Action {
    val car = DB.query[Car].fetchOne().get
    Ok(views.html.one_car(car))
  }

  def deleteCar = Action {
    val car = DB.query[Car].fetchOne().get
    DB.delete[Car](car)
    Ok("{'result': 'ok'}")
  }

}
