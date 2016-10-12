package Exercise6

/**
  * Created by simonpersson on 2016-10-04.
  */
class Square (val point: Point, val side: Int) {
  val area: Int = side * side

  def move (dPoint: Point): Square = Square(Point(point.x + dPoint.x, point.y + dPoint.y), side)

  def isEqualSizeAs (that: Square): Boolean = that.side == this.side

  def scale (factor: Double): Square = {
    val factorSide: Double = side * factor
    Square(point, (factorSide.toInt))
  }

  override def toString: String = s"The square is located at (${point.x}, ${point.y}) and each side is $side pixels"

}

object Square {
  val unit: Square = Square(Point.origin,1)

  def apply(point: Point, side: Int): Square = new Square(point, side)

  def apply(): Square = unit
}
