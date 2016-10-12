package Lab5.shuffle

/**
  * Created by simonpersson on 28/09/16.
  */
object AsciiBarGraph {
  import collection.mutable.Map
  def apply(f: Map[String, Int], termSize: Int = 80): String = {

    val maxDigits = f.values.map(_.toString.length).max
    val maxVal = f.values.max
    val longestName = f.keys.map(_.length).max
    val barLengthMax = termSize -
      (maxDigits + longestName + ": ".length + " ".length)
    val squareVal = maxVal.toDouble / barLengthMax

    val lines = for (v <- f.keys.toList) yield v +
      ": " +
      " " * (longestName + maxDigits - v.length - f(v).toString.length) +
      f(v) +
      " " +
      "#" * (f(v).toDouble / squareVal).toInt
    lines.sorted.mkString("\n")
  }
}
