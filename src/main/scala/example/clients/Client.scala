package example.clients

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import example.resources.{CatResource, DefaultResource}

import scala.io.StdIn

class Client(host: String, port: Int) extends DefaultResource with CatResource {
  implicit val system = ActorSystem("s3-client")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route: Route = defaultRoute ~ catRoutes

  def start() = {
    val bindingFuture = Http().bindAndHandle(route, host, port)

    println(s"Server online at http://$host:$port\nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
