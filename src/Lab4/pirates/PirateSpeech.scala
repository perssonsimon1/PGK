package Lab4.pirates

/**
  * Created by simonpersson on 23/09/16.
  */
object PirateSpeech {
  def main(args: Array[String]): Unit = {

    val filename = if (!args.isEmpty) args(0) else "util/skattkammaron.txt"

    val wordMap = readBook(filename)

    val wordToLookup = "jim"

    println(s"The most common is ${wordToLookup.toUpperCase} ${wordMap.apply(wordToLookup).toString}")

  }

  def readBook(bookFile: String): Map[String, WordCounter] = {
    val wordVector = FileUtil.readWords(bookFile)
    val words = wordVector.distinct

    val counterMap = words.map(word => (word -> new WordCounter())).toMap

    for (pair <- wordVector.sliding(2)) {
      val first = pair(0)
      val second = pair(1)

      counterMap.apply(first).addWord(second)

    }

    counterMap
  }

}
