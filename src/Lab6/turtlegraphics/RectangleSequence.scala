package Lab6.turtlegraphics

case class RectangleSequence(rectangle: Rectangle,
                             count: Int,
                             startAngle: Double,
                             step: Double,
                             rotationStep: Double,
                             scaleStep: Double) {

  /** Draws the image using a given Turtle */
  def draw(turtle: Turtle): Unit = ???

  /** Returns a new RectangleSequence that has been scaled with a size factor */
  def scale(factor: Double): RectangleSequence = ???

  /** Returns a new RectangleSequence that has been rotated to the left */
  def rotateLeft(degrees: Double): RectangleSequence = ???

  /** Returns a new RectangleSequence that has been rotated to the right */
  def rotateRight(degrees: Double): RectangleSequence = ???

  /** Returns a new RectangleSequence that has been moved a number of pixels */
  def translate(dx: Double, dy: Double): RectangleSequence = ???
}
