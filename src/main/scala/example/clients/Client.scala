package example.clients

import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.io.StdIn
import scala.concurrent.Future
import example.model.Cat
import example.model.CatJsonSupport._
import example.services.{CatService, S3Client}

class Client(host: String, port: Int) {
  private val catService = new CatService(new S3Client)
  implicit val system = ActorSystem("s3-client")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route: Route =
    get {
      path("") {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Welcome on the home page</h1>"))
      }
    } ~
    get {
      path("hello") {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<p>Say Hello</p>"))
      }
    } ~
    post {
      path("cat") {
        entity(as[Cat]) { cat =>
          val saved: Future[Done] = catService.saveCat(cat)
          onComplete(saved) { done =>
            complete(cat)
          }
        }
      }
    }

  def start() = {
    val bindingFuture = Http().bindAndHandle(route, host, port)

    println(s"Server online at http://$host:$port\nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
