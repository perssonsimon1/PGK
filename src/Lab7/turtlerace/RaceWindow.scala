package Lab7.turtlerace

import cslib.window._

class RaceWindow extends SimpleWindow(800, 600, "Tävlingsfönster"){
  private val startX = 100
  private val endX = 700
  val noOfTurtles = 8
  /**
    * Draws a race in the RaceWindow
    */
  def draw: Unit = {
    moveTo(getStartX/2, 0)
    setLineWidth(getStartX)
    setLineColor(java.awt.Color.CYAN)
    lineTo(getStartX/2, 400)
    moveTo(getEndX+(800-getEndX)/2, 0)
    setLineColor(java.awt.Color.CYAN)
    lineTo(getEndX+(800-getEndX)/2, 400)
    setLineColor(java.awt.Color.BLACK)
    for(i <- 1 to noOfTurtles){
      moveTo(getStartX-10, getStartY(i) )
      writeText( i.toString )
    }
    setLineWidth(1)
  }


  /**
    * Returns the Y-coordinate for the turtle with start number n
    */
  def getStartY(n: Int): Int = {
    val topY = 100
    val botY = 400

    val range = botY - topY
    val step = range / noOfTurtles

    val turtleY = topY + step * (n-1)
    turtleY
  }

  /**
    * Returns the X-coordinate of the starting position
    */
  def getStartX: Int = startX

  /**
    * Returns the X-coordinate of the finish line
    */
  def getEndX: Int = endX

  def printRacers(list: Array[RaceTurtle], x: Int, title: String): Unit = {
    var tempY = 420
    moveTo(x, tempY)
    writeText(title)
    for (i <- list.indices) {
      tempY += 20
      moveTo(x, tempY)
      writeText(s"${i+1}. ${list(i).toString}")
    }
  }

  def printTitle(title: String): Unit = {
    moveTo(150,50)
    writeText(title)
  }
}
