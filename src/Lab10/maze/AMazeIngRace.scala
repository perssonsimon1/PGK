/**
  * Created by simonpersson on 2016-11-17.
  */

package Lab10.maze

import java.awt.Color

import cslib.window.SimpleWindow

object AMazeIngRace {

  def printMazesFromDir(path: String): Unit = {
    for (mazeNbr <- 1 to 5) {
      val filename = s"Maze$mazeNbr.txt"
      val maze = Maze.fromFile(path + filename)
      println(maze)
    }
  }

  def drawMazesInDir(path: String, window: SimpleWindow): Unit = {
    for (mazeNbr <- 1 to 5) {
      val filename = s"Maze$mazeNbr.txt"
      window.moveTo(10, 100)
      window.writeText(s"Click to draw $path$filename")
      window.waitForMouseClick()
      window.clear()

      //Run this code when draw in Maze and walk in MazeTurtle are implemented
      val maze = Maze.fromFile(path + filename)
      maze.draw(window)
      val turtle = new MazeTurtle(window, maze, Color.MAGENTA)
      window.moveTo(10, 50)
      window.writeText(s"Click to walk!")
      window.waitForMouseClick()
      turtle.walk()
      window.waitForMouseClick()

    }
  }

  def createAndDrawRandomMaze(rows: Int, cols: Int, window: SimpleWindow): Unit = {
    if (rows < 20 || cols < 20) {
      println("Please choose a larger value for rows and cols!")
    } else {
      window.clear()
      window.moveTo(10, 40)
      window.writeText(s"Click to draw random maze of size ($rows,$cols)")
      window.waitForMouseClick()

      //Run this code when random in Maze is implemented
      /*
      val maze = Maze.random(rows, cols)
      maze.draw(window)

      val turtle = new MazeTurtle(window, maze, Color.RED)
      window.moveTo(10, 70)
      window.writeText(s"Click to walk!")
      window.waitForMouseClick()
      turtle.walk()
      */
    }
  }

  /*def getResourcePath : String = getClass.getResource("/").getPath */
  def getResourcePath : String = "util/"

  def main(args: Array[String]): Unit = {
    val path = getResourcePath
    println(s"Resource path detected: $path")
    val w = new SimpleWindow(800, 800, "A-Maze-Ing Race")
    printMazesFromDir(path)
    w.moveTo(10, 100)
    w.writeText(s"Printed mazes in $path")
    w.moveTo(10, 200); w.writeText("CLICK TO CONTINUE!")
    w.waitForMouseClick()
    w.clear()
    drawMazesInDir(path, w)
    createAndDrawRandomMaze(50, 50, w) //recommended sizes is between 20-100
    w.moveTo(10, 20); w.writeText("GOODBYE! File -> Quit or Ctrl+Q to exit.")
  }

}
