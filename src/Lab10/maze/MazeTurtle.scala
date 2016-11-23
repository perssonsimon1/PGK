package Lab10.maze

import java.awt.Color

import cslib.window.SimpleWindow


/**
  * Created by simonpersson on 2016-11-18.
  */
class MazeTurtle (window: SimpleWindow,
                  maze: Maze,
                  color: Color
                 ) extends ColorTurtle (
  window,
  initPosition = Point(maze.getXEntry(), maze.getYEntry()),
  initColor = color
) {



  def walk(): Unit = {
    while(!maze.atExit(x,y)) {
      if(maze.wallAtLeft(dir,x,y)) {
        if(maze.wallInFront(dir,x,y)) {
          turnRight(90)
          forward(1)
        } else {
          forward(1)
        }

      } else {
        turnLeft(90)
        forward(1)
      }
    }
  }


}
