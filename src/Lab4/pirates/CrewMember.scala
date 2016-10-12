package Lab4.pirates

/**
  * Created by simonpersson on 22/09/16.
  */
case class CrewMember (firstname: String, lastname: String, post: String) {

  override def toString: String = s"$firstname $lastname, $post"

}
