package Lab7.turtlerace

/**
  * Created by simonpersson on 2016-10-11.
  */

import java.awt.Color
import javax.swing.JOptionPane

object TurtleTournament {

  def main(args: Array[String]): Unit = {
    startTournament()
  }

  def randTurtle(rw: RaceWindow, index: Int, name: String): RaceTurtle = {
    val rnd = scala.util.Random
    val capacities = Vector(
      new RaceTurtle(rw, index, name, Color.RED) with AbsentMindedness,
      new RaceTurtle(rw, index, name, Color.GREEN) with Dizziness,
      new RaceTurtle(rw, index, name, Color.BLUE) with Mole,
      new RaceTurtle(rw, index, name, Color.MAGENTA) with Showoff
    )

    capacities(rnd.nextInt(4))//Set 4 to include Showoff
}

  def startTournament (): Unit = {
    val window = new RaceWindow()
    window.draw

    val allTurtles: Array[RaceTurtle] = new Array(32)
    for (i <- 1 to 32) {
      allTurtles(i-1) = randTurtle(window, i, s"Turtle $i")
    }

    val turtleVector = allTurtles.toVector

    val winners: Array[List[RaceTurtle]] = new Array(7)


    for(i <- 8 to 32 by 8) {
      window.clear
      window.draw
      val racingTurtles: Seq[RaceTurtle] = turtleVector.slice(i-8, i)
      winners((i/8) - 1) = TurtleRace.race(racingTurtles, window, s"Quarterfinals : ${i/8}")
    }

    for(i <- 0 to 2 by 2) {
      window.clear
      window.draw
      val racingTurtles: Seq[RaceTurtle] = winners(i).slice(0,4) ++ winners(i+1).slice(0,4)
      winners(if(i == 0) 4 else 5) = TurtleRace.race(racingTurtles, window, s"Semi final :${if(i == 0) 1 else 2}")
    }

    window.clear
    window.draw
    val racingTurtles: Seq[RaceTurtle] = winners(4).slice(0,4) ++ winners(5).slice(0,4)
    winners(6) = TurtleRace.race(racingTurtles, window, "Final Race")

    JOptionPane.showMessageDialog(null, s"The winner is ${winners(6).head.name}")
  }
}
