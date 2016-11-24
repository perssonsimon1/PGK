package Lab11.survey

/**
  * Created by simonpersson on 2016-11-24.
  */
import java.net.MalformedURLException
import java.nio.file.{Files, Path, Paths}

import scala.io.Source
import scala.util.Try

/**
  * A representation of text data in matrix form.
  *
  * @param matrix   The data. Rows in the outer Vector, columns in the inner.
  * @param headings Column headings.
  * @param separator      The String character that separates the columns.
  */
case class Table(matrix: Vector[Vector[String]],
                 headings: Vector[String],
                 separator: String) {

  /** Returns the number of (rows, columns) of the matrix data. */
  val dim: (Int, Int) = if(matrix.length > 0) (matrix.length, matrix(0).length) else (0,0)


  /** Returns the values from a specified column. */
  def col(c: Int): Vector[String] = {
    matrix.map(x => x(c))
  }

  /** Returns the matrix in string format using separator between columns*/
  override lazy val toString: String =  {
    var out: String = ""
    matrix.foreach(x => out += x.mkString(",") + "\n" )
    out
  }

  /** A new Table with rows sorted on column c (implemented using sortBy). */
  def sort(c: Int): Table = Table(matrix.sortBy(_(c)), headings, separator)

  /** A new Table with rows sorted on column c (implemented from scratch). */
  def mySort(c: Int): Table = {

    /** Returns true if s1 should be sorted before s2, otherwise false*/
    def compare(s1: String, s2: String): Boolean = {
      val res = s1.compareTo(s2)
      if (res < 0) true
      else if (res > 0) false
      else false
    }

    val sortArray: Array[Array[String]] = matrix.map(x => x.toArray).toArray

    /** Performing insertion sort on vector*/
    for(index <- 1 until sortArray.length) {
      val cur: String = sortArray(index)(c)
      var u: Int = index
      while(u > 0 && compare(cur, sortArray(u - 1)(c))){
        sortArray(u)(c) = sortArray(u-1)(c)
        u -= 1
      }
      sortArray(u)(c) = cur
    }
    val newMatrix = sortArray.map(x => x.toVector).toVector
    Table(newMatrix, headings, separator)
  }

  /**
    * A new Table filtered so that column c only contains the wanted values.
    */
  def filter(c: Int, wanted: Vector[String]): Table = Table(matrix.filter(x => wanted.contains(x(c))),headings,separator)


  /**
    * Returns the distinct values for the given column coupled with the number
    * of occurrences for that value. The pairs are sorted descending on the
    * number of occurrences. The first element is the column header together
    * with the total number of occurrences for all values.
    */
  def register(c: Int): Vector[(String, Int)] = {
    val regVec: Vector[(String, Int)] = matrix.groupBy(a => a(c)).map(t => (t._1, t._2.length)).toVector
    val out = (headings(c), matrix.length) +: regVec.sortWith(_._2 > _._2)
    out
  }
}

object Table {
  def getResourcePath(resource: String): Path = {
    Paths.get(getClass.getResource("/" + resource).toURI)
  }

  /**
    * Reads column separated data from either a file or a URL into a Table.
    *
    * @param uri The location of the data.
    * @param sep The character that separates the columns.
    */
  def fromFile(uri: String, sep: String): Table = {
    val source = Try {
      Source.fromURL(uri)
    }.recover {
      case _: MalformedURLException =>
        Source.fromFile(getResourcePath("").resolve(uri).toFile)
    }
    fromFile(source.get, sep)
  }

  /**
    * Reads column separated text data from a Source into a Table.
    *
    * @param source The data to load.
    * @param sep The character that separates the columns.
    */
  def fromFile(source: Source, sep: String): Table = {
    val lines = source.getLines.toVector
    val rows = lines.map(_.split(sep).toVector)
    Table(rows.tail, rows.head, sep)
  }

  /** Write a Table to disk. */
  def save(path: String, table: Table): Unit = {
    save(Paths.get(path), table)
  }

  /** Write a Table to disk. */
  def save(path: Path, table: Table): Unit = {
    import java.nio.charset.StandardCharsets
    println(s"Saving table to file: ${path.toAbsolutePath}")
    Files.write(path, table.toString.getBytes(StandardCharsets.UTF_8))
  }

  /** Some tests of Table */
  def main(args: Array[String]): Unit = {
    val path = getResourcePath("favorit.csv")
    println(s"***TESTING Table***\nInput: $path")
    val table = Table.fromFile(Source.fromFile(path.toFile), ",")
    val tableFiltered = table.filter(4, Vector("Linux")).sort(6)
    println(tableFiltered.register(6).mkString("\n"))
    save(getResourcePath("out.csv"), tableFiltered)
  }
}
