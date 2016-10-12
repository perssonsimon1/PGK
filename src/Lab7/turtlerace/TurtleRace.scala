package Lab7.turtlerace

import javax.swing.JOptionPane
import scala.collection.mutable.ArrayBuffer

object TurtleRace {
  /**
    * Perform a race between eight turtles and returns the turtles in finishing order
    */
  def race(turtles: Seq[RaceTurtle], rw: RaceWindow, title: String): List[RaceTurtle] = {
    rw.printTitle(title)

    for(i <- turtles.indices){
      turtles(i).nbr = i + 1
      turtles(i).restart
    }

    rw.printRacers(turtles.toArray,20, "Racers")

    var winners = new ArrayBuffer[RaceTurtle]

    while (winners.length < 8) {
      for (i <- turtles.indices) {
        if (!winners.contains(turtles(i))) {
          turtles(i).raceStep
          delay(3)
          if (turtles(i).x >= rw.getEndX) {
            winners += turtles(i)
          }
        }
      }
    }

    rw.printRacers(winners.toArray, 370, "Winners")
    rw.waitForMouseClick()
    winners.toList
  }

  def delay (time: Int): Unit = {
    if(time > 0) {
      try
        Thread.sleep(time.toLong)

      catch {
        case time: InterruptedException =>
      }
    }
  }

}
