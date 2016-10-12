package Lab7.turtlerace

import javax.swing.JOptionPane
import scala.collection.mutable.ArrayBuffer

object TurtleRace {
  /**
    * Perform a race between eight turtles and returns the turtles in finishing order
    */
  def race(turtles: Seq[RaceTurtle], rw: RaceWindow, title: String): List[RaceTurtle] = {
    rw.printTitle(title)
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
    JOptionPane.showMessageDialog(null, s"Press OK to continue", "Race finished", JOptionPane.INFORMATION_MESSAGE)
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
