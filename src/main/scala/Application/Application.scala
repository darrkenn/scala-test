package Application

import upickle.default.*

import scala.concurrent.Await
import scala.concurrent.duration.Duration

case class newUser(name: String, password: String) derives ReadWriter
case class newPost(text: String, userId: Int) derives ReadWriter
case class User(id: Int, name: String) derives ReadWriter
case class Post(id: Int, text: String, userId: Int) derives ReadWriter

object Application extends cask.MainRoutes:

  try {
    db.Database.setVariables()
    db.Database.setupDatabase()
  } catch  {
    case e: Exception =>
      println(e)
      System.exit(1)
  }




  @cask.get("/")
  def hello(): String = "Hello"

  @cask.post("/api/new-user")
  def createNewUser(request: cask.Request): cask.Response[String] =
    try {
      val user: newUser = read[newUser](request.text())
      db.Database.createUser(user.name, user.password)
      cask.Response("User created", 200)
    }
    catch {
      case e: Exception =>
        println(e)
        cask.Response("Error", 500)
    }

  @cask.post("/api/new-post")
  def createNewPost(request: cask.Request): cask.Response[String] =
    try {
      val post: newPost = read[newPost](request.text())
      db.Database.createPost(post.text, post.userId)
      cask.Response("Post created", 200)
    }
    catch {
      case e: Exception =>
        println(e)
        cask.Response("Error", 500)
    }

  @cask.get("/api/get-users")
  def getUsers(): cask.Response[String] =
    val users = Await.result(db.Database.getUsers, Duration.Inf)
    cask.Response(upickle.default.write(users), 200)


  @cask.get("/api/get-posts")
  def getPosts(): cask.Response[String] =
    val posts = Await.result(db.Database.getPosts, Duration.Inf)
    cask.Response(upickle.default.write(posts), 200)


  initialize()



  /*
  @cask.post("/post")
  def reverseText(request: cask.Request): String = request.text().reverse

  @cask.get("/test")
  def convertDbVariablesToString(): String = db.Database.getVariables.mkString("/")

  @cask.post("/post/test")
  def setDbVariables(request: cask.Request): cask.Response[String] = {
    try {
      val values: JsonValues = read[JsonValues](request.text())
      db.Database.setVariables(values.url, values.user, values.password)
      cask.Response("Variables set", 200)
    } catch {
      case e: Exception =>
        println(e)
        cask.Response("Error occured", 500)
    }
  }

  @cask.post("/debug/content-type")
  def debug(request: cask.Request): cask.Response[String] =
    val raw = request.headers.toSeq.sortBy(_._1).map { case (k, vs) => s"$k: ${vs.mkString(",")}" }.mkString("\n")
    cask.Response(raw, 200)

   */