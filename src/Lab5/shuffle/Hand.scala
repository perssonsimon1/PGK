package Lab5.shuffle

/**
  * Created by simonpersson on 28/09/16.
  */
case class Hand(cards: Vector[Card]) {
  def isFlush: Boolean = cards.forall(_.suit == cards.head.suit)

  def isStraight: Boolean = {
    val sortedVals = cards.toVector.map(_.value).sorted
    def sequential(v: Vector[Int]) =
      (0 until v.length - 1).forall(i => sortedVals(i) + 1 == sortedVals(i + 1))
    if (sortedVals(0) != 1) {
      sequential(sortedVals)
    } else {
      sequential(sortedVals) ||
        sequential(sortedVals.drop(1).:+(14))
    }
  }


  /**
    * Yields a vector of length 14, with positions 1-13 containing
    * the number of cards of that value
    */
  def tally: Vector[Int] = {
    var tallyTemp = new Array[Int](14)
    val temp = cards.groupBy(x => x.value)
    for ((a,b) <- temp) {
      tallyTemp(a-1) = b.length
    }
    tallyTemp.toVector
  }
}
object Hand {
  def drawFrom(deck: CardDeck) = Hand(deck.cards.take(5).toVector)
}
