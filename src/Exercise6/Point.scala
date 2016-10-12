package Exercise6

/**
  * Created by simonpersson on 2016-10-04.
  */
case class Point (x: Int, y: Int) {
  def distanceTo(point: Point): Double = {
    math.hypot(point.x - x, point.y - y)
  }

  override def toString: String = s"Point ($x, $y)"
}

object Point {
  val origin = Point(0,0)
}