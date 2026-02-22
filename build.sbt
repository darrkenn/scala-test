ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.8.1"


lazy val root = (project in file("."))
  .settings(
      name := "scala-http",
      libraryDependencies += "com.lihaoyi" %% "cask" % "0.11.3",
      libraryDependencies += "org.postgresql" % "postgresql" % "42.7.10",
      libraryDependencies += "com.typesafe.slick" %% "slick" % "3.6.1",
      libraryDependencies += "org.slf4j" % "slf4j-simple" % "2.0.17",
      Compile / run / fork := true
  )
