package Application


object Application extends cask.MainRoutes:

  try {
    db.Database.setVariables()
  } catch  {
    case e: Exception =>
      println(e)
      System.exit(1)
  }



  @cask.get("/")
  def hello(): String = "Hello"


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


  initialize()
