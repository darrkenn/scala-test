package Application.db

import cats.effect.IO
import doobie.util.transactor.Transactor


object Database:
  private var url: String = ""
  private var port: String = ""
  private var user: String = ""
  private var password: String = ""
  private def getTransactor: Transactor[IO] =
    Transactor.fromDriverManager[IO](
      driver = "org.postgresql.Driver",
      url,
      user,
      password,
      logHandler = None
    )

  def setVariables(): Unit =
    url = getEnvVariable("SCALA_URL")
    port = getEnvVariable("SCALA_PORT")
    user = getEnvVariable("SCALA_USER")
    password = getEnvVariable("SCALA_PASSWORD")

  private def getEnvVariable(name: String): String =
    Option(System.getenv(name)).map(_.trim).filter(_.nonEmpty).getOrElse(throw new IllegalArgumentException(s"Missing env var: $name"))


  def getVariables: List[String] =
    List(url,user,password)





