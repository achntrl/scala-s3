package example.services

import akka.Done
import spray.json._
import example.model.Cat

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import example.model.CatJsonSupport._

object CatService {
  val s3Client: S3Client = new S3Client

  def createCat(cat: Cat) = {
    val catAst = cat.toJson

    s3Client.putObject(s"${cat.name}.txt", catAst.prettyPrint)
    Future {Done}
  }

  def getCat(name: String) = {
    s3Client.getObject(s"$name.txt")
  }
}
