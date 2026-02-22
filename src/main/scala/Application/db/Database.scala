package Application.db


import slick.jdbc.PostgresProfile.api.*
import Application.db

object Database:
  private var hostname: String = ""
  private var databaseUser: String = ""
  private var password: String = ""
  private var databaseName: String = ""
  private var port: String = ""

  def setVariables(): Unit =
    hostname = getEnvVariable("POSTGRES_HOSTNAME")
    databaseUser = getEnvVariable("POSTGRES_USER")
    password = getEnvVariable("POSTGRES_PASSWORD")
    databaseName = getEnvVariable("POSTGRES_NAME")
    port = getEnvVariable("POSTGRES_PORT")

  //Gets environment variable, if empty it will throw an exception
  private def getEnvVariable(name: String): String =
    Option(System.getenv(name)).map(_.trim).filter(_.nonEmpty).getOrElse(throw new IllegalArgumentException(s"Missing env var: $name"))

  private def connectDatabase(): Database =
    slick.jdbc.PostgresProfile.api.Database.forURL(
      s"jdbc:postgresql://$hostname:$port/$databaseName",
      databaseUser,
      password,
      driver = "org.postgresql.Driver"
    )

  def setupDatabase(): Unit =
    val schemaSetup = DBIO.seq(
      (db.users.schema ++ db.posts.schema).createIfNotExists
    )
    val databaseConnection: Database = connectDatabase()
    databaseConnection.run(schemaSetup)
    databaseConnection.close()


  def createUser(name: String, password: String): Unit =
    val insertUser =  DBIO.seq(
      users += (0,name,password)
    )
    val databaseConnection: Database = connectDatabase()
    databaseConnection.run(insertUser)
    databaseConnection.close()
