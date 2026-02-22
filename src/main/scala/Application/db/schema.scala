package Application.db

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

class Users(tag: Tag) extends slick.jdbc.PostgresProfile.Table[(Int, String, String)](tag, "USERS") {
  def id = column[Int]("USER_ID", O.PrimaryKey, O.AutoInc, O.Unique)

  def name = column[String]("NAME", O.Unique)

  def password = column[String]("PASSWORD")

  def * = (id, name, password)
}

val users = TableQuery[Users]

class Posts(tag: Tag) extends slick.jdbc.PostgresProfile.Table[(Int, String, Int)](tag, "POSTS") {
  def id = column[Int]("POST_ID", O.PrimaryKey, O.AutoInc, O.Unique)
  def text = column[String]("TEXT")
  def userId = column[Int]("USER_ID")
  def user = foreignKey("USER_ID", userId, users)(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
  def * = (id,text,userId)
}
val posts = TableQuery[Posts]