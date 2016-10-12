package Lab7.turtlerace

import Lab6.turtlegraphics.Point
import java.awt.Color


class RaceTurtle(private val window: RaceWindow, var nbr: Int, val name: String, color: Color)
  extends ColorTurtle(window, color, name) {

  /**
    * Takes one step of a random length 1 to 5
    */
  def raceStep(): Unit = {
    forward((math.random * 5) + 1)
  }

  /**
    * Restarts the turtle at the finish line.
    * To be used before each race
    */
  def restart: Unit = {
    val initPos = new Point(window.getStartX, window.getStartY(nbr))
    jumpTo(initPos)
  }

  override def toString: String = {
    s" $name: "
  }


}


trait Dizziness extends RaceTurtle {
  val _dizziness = (1.0 + math.random * 5).toInt

  override def raceStep(): Unit = {
    val shouldTurnRight: Boolean = math.random > 0.5
    val degreesToTurn = _dizziness.toDouble * (math.random * 90.0 / 5.0)

    if (shouldTurnRight) {
      turnRight(degreesToTurn)
    } else {
      turnLeft(degreesToTurn)
    }

    super.raceStep()

    if (shouldTurnRight) {
      turnLeft(degreesToTurn)
    } else {
      turnRight(degreesToTurn)
    }
  }

  override def toString: String = {
    super.toString() + s"Dizzy [${_dizziness}]"
  }
}

trait AbsentMindedness extends RaceTurtle {
  val _absentMindedness = math.random

  override def raceStep(): Unit = {
    val senseOfPurpose = math.random

    if (senseOfPurpose > _absentMindedness) {
      super.raceStep()
    }
  }

  override def toString: String = {
    super.toString() + s"AbsentMinded [${_absentMindedness * 100}%]"
  }
}

trait Mole extends RaceTurtle {
  override def raceStep(): Unit = {
    val pencilUp = math.random > 0.5
    if (pencilUp) {
      penUp()
    } else {
      penDown()
    }

    super.raceStep()
  }

  override def toString: String = {
    super.toString() + "Mole [50‰]"
  }
}

trait Showoff extends RaceTurtle {
  val _needForAttention = math.random

  override def raceStep(): Unit = {
    val discipline = math.random
    val doLoop = discipline < _needForAttention
    if (doLoop) {
      walkInCircle((_needForAttention - discipline) * 30)
    } else {
      super.raceStep()
    }

  }

  override def toString: String = {
    super.toString() + s"Showoff [${_needForAttention * 100}%]"
  }
}

