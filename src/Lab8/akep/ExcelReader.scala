package Lab8.akep

/**
  * Created by simonpersson on 2016-11-02.
  */
object ExcelReader {
  def main(args: Array[String]): Unit = {
    val djur1 = new djur_annat("321", "20161017", "1283", 53, "N", 22.40, "U-", "3", "", "6503182732", "", "66121")
    val djur2 = new djur_annat("321", "20161017", "1283", 53, "N", 22.40, "U-", "3", "", "6503182732", "", "66121")
    val djur3 = new djur_annat("321", "20161017", "1283", 53, "N", 22.40, "U-", "3", "", "6503182732", "", "66121")
    val djur4 = new djur_annat("321", "20161017", "1283", 53, "N", 22.40, "U-", "3", "", "6503182732", "", "66121")

    println(djur1.toTXTFormat)
    println(djur2.toTXTFormat)
    println(djur3.toTXTFormat)
    println(djur4.toTXTFormat)
  }

}
