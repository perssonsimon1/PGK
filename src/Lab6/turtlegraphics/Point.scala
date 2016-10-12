package Lab6.turtlegraphics

/**
  * Created by simonpersson on 2016-10-05.
  */
case class Point (x: Double, y: Double) {
  def translate (dx: Double, dy: Double): Point = {
    Point(x + dx, y + dy)
  }
}
