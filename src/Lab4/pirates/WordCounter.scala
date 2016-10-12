package Lab4.pirates

/**
  * Created by simonpersson on 23/09/16.
  */
class WordCounter {

  var words = Map[String, Int]()

  def addWord(word: String): Unit = {
    if (words.contains(word)) {
      val count: Int = (words apply word) + 1
      words = words.updated(word, count)
    }
    else {
      words += (word -> 1)
    }
  }

  def mostCommonWord: (String, Int) = {
    words.maxBy(_._2)
  }

  override def toString: String = {
    val temp: (String, Int) = mostCommonWord
    s"${temp._1.toUpperCase} (it occurred ${temp._2} times)"
  }
}
