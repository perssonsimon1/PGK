package Lab6.turtlegraphics

/** Immutable class representing a rectangle.
  * @param position a Point representing the upper left corner of the rectangle
  *                 (before rotation)
  * @param width    the width of the rectangle
  * @param height   the height of the rectangle
  * @param angle    the angle of the rectangle (rotated around the upper
  *                 left corner) Positive degrees indicate a counter clockwise
  *                 rotation measured from the X-axis
  */
case class Rectangle(private val position: Point, private val width: Double, private val height: Double, private val angle: Double) {
  /** Draws the rectangle using a turtle */
  def draw(turtle: Turtle): Unit = {
    turtle.penUp()
    turtle.jumpTo(position)
    turtle.turnNorth()
    turtle.turnRight(angle)
    turtle.penDown()
    for(i <- 1 to 2) {
      turtle.forward(width)
      turtle.turnRight(90)
      turtle.forward(height)
      turtle.turnRight(90)
    }
  }

  /** Returns a new Rectangle that is rotated to the left */
  def rotateLeft(degrees: Double): Rectangle = {
    val newAngle = (angle + degrees) % 360
    Rectangle(position,width,height,newAngle)
  }

  /** Returns a new Rectangle that is rotated to the right */
  def rotateRight(degrees: Double): Rectangle = {
    val newAngle = (angle - degrees) % 360
    Rectangle(position,width,height,newAngle)
  }

  /** Returns a new Rectangle that has been scaled by a size factor */
  def scale(factor: Double): Rectangle = {
    Rectangle(position,width*factor,height*factor,angle)
  }

  /** Returns a new Rectangle that has been moved some number of pixels */
  def translate(dx: Double, dy: Double): Rectangle = {
    Rectangle(Point(position.x + dx, position.y + dy),width,height,angle)
  }
}
