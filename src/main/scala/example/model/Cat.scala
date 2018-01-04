package example.model

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

case class Cat(name: String, pictureUrl: String)

object CatJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val catFormat = jsonFormat2(Cat)
}
