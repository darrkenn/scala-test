ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.8.1"


lazy val root = (project in file("."))
  .settings(
    name := "scala-http",
    libraryDependencies += "com.lihaoyi" %% "cask" % "0.11.3",
    libraryDependencies += "org.tpolecat" %% "doobie-core" % "1.0.0-RC11",
    libraryDependencies += "org.tpolecat" %% "doobie-postgres" % "1.0.0-RC11",
    Compile / run / fork := true
  )
