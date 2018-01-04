name := "ScalaS3Api"

version := "0.1"

scalaVersion := "2.12.4"

// AWS
libraryDependencies += "com.amazonaws" % "aws-java-sdk-s3" % "1.11.255"
libraryDependencies += "com.typesafe" % "config" % "1.3.1"

// Akka
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.17"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.0.5"
libraryDependencies += "com.typesafe.akka" %% "akka-http-core" % "10.0.5"