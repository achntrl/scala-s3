package example.services

import akka.Done
import spray.json._
import example.model.Cat

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import example.model.CatJsonSupport._

class CatService(s3Client: S3Client) {
  def saveCat(cat: Cat) = {
    val catAst = cat.toJson

    s3Client.putObject(s"${cat.name}.txt", catAst.prettyPrint)
    Future {Done}
  }
}
