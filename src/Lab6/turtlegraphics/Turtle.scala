package Lab6.turtlegraphics

import java.awt.Color

import cslib.window.SimpleWindow

/** A Kojo-like Turtle class that can be used to draw shapes in a SimpleWindow.
  *
  * @param window    The window the turtle should be placed in.
  * @param position  A Point representing the turtle's starting coordinates.
  * @param angle     The angle between the turtle direction and the X-axis
  *                  measured in degrees. Positive degrees indicate a counter
  *                  clockwise rotation.
  * @param isPenDown A boolean representing the turtle's pen position. True if
  *                  the pen is down. */
class Turtle(window: SimpleWindow,
             private var position: Point,
             private var angle: Double,
             private var isPenDown: Boolean) {

  def this (window: SimpleWindow) {
    this(window, Point(0,0), 0, true)
  }

  /** Gets the Turtle's current pixel position on the x axis */
  def x: Int = math.round(position.x).toInt

  /** Gets the Turtle's current pixel position on the y axis
    * (measured from the top left) */
  def y: Int = math.round(position.y).toInt

  /** Moves the turtle to a new position without drawing a line. */
  def jumpTo(newPosition: Point) = {
    position = newPosition
  }

  /** Moves the turtle forward in its current direction, drawing a line if
    * the pen is down.
    * @param length The number of pixels to move forward. */
  def forward(length: Double): Unit = {
    window.moveTo(x, y)
    val newX = position.x + length * math.cos(angle.toRadians)
    val newY = position.y + length * -math.sin(angle.toRadians)
    position = Point(newX, newY)
    if(isPenDown) window.lineTo(x, y) else window.moveTo(x, y)

  }

  /** Turns the turtle to the left.
    *
    * @param turnAngle The number of degrees to turn. */
  def turnLeft(turnAngle: Double): Unit = {
    angle = angle + turnAngle
    angle = angle % 360
  }

  /** Turns the turtle to the right.
    *
    * @param turnAngle The number of degrees to turn. */
  def turnRight(turnAngle: Double): Unit = {
    angle = angle - turnAngle
    angle = angle % 360
  }

  /** Turns the turtle straight up. */
  def turnNorth(): Unit = {
    angle = 90
  }

  /** Sets the turtle's pen down, causing it to draw lines when it moves */
  def penDown(): Unit = {
    isPenDown = true
  }

  /** Lifts the turtle's pen, preventing it from drawing lines. */
  def penUp(): Unit = {
    isPenDown = false
  }

  def walkInCircle( radius: Double ): Unit = {

    val startAngle = angle;
    val startPos = position;
    turnLeft( 90 );
    val noOfSteps = (radius*2.0*math.Pi).toInt;

    //val stepLen = math.Pi*radius/360;
    val stepLen = radius*2.0*math.Pi/noOfSteps.toDouble;
    val turn = 360.0/noOfSteps;
    for(i <- 0 to noOfSteps){
      forward(stepLen);
      turnRight(turn);
    }
    turnRight( 90 );

    position = startPos;
    angle = startAngle;
  }

}