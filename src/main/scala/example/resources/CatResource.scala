package example.resources

import akka.Done
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import example.model.Cat
import example.services.{CatService, S3Client}
import example.model.CatJsonSupport._

import scala.concurrent.Future

trait CatResource {
  def catRoutes: Route = pathPrefix("cats") {
    pathEnd {
      post {
        entity(as[Cat]) { cat =>
          val saved: Future[Done] = CatService.createCat(cat)
          onComplete(saved) { done =>
            complete(cat)
          }
        }
      }
    } ~
    path(Segment) { name =>
      get {
        complete(CatService.getCat(name))
      }
    }
  }
}
