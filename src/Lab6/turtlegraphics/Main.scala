package Lab6.turtlegraphics

import cslib.window.SimpleWindow

object Main {
  def main(args: Array[String]): Unit = {
    // Create the window and turtle objects
    val window = new SimpleWindow(1000, 1000, "Turtlegraphics")
    val t = new Turtle(window, Point(0, 0), 0, false)

  }
}
