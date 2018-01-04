package example.resources

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

trait DefaultResource {
  def defaultRoute: Route = {
    get {
      path("") {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Welcome on the home page</h1>"))
      }
    } ~
    get {
      path("hello") {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<p>Say Hello</p>"))
      }
    }
  }
}
