package Lab5.shuffle

/**
  * Created by simonpersson on 28/09/16.
  */

import java.util.Random

class CardDeck {
  val rand = new util.Random

  lazy val cards: Array[Card] = {
    for{
      suit <- 1 to 4
      value <- 1 to 13
    } yield {
      Card(suit,value)
    }
  }.toArray

  def shuffle (): Unit = {
    val len = cards.length
    for (i <- (len - 1) to 0 by -1) {
      val rnd = new Random()
      val r = rnd.nextInt(i + 1)
      val temp = cards(i)
      cards(i) = cards(r)
      cards(r) = temp
    }
  }
}

object CardDeck {
  def main(args: Array[String]): Unit = {
    val d = new CardDeck
    println(d.cards.mkString(" "))
    d.shuffle()
    println()
    println(d.cards.mkString(" "))
  }
}
