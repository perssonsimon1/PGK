package Exercise6

/**
  * Created by simonpersson on 2016-10-04.
  */
object SquareTest {

  def main(args: Array[String]): Unit = {
    val (s1,s2) = (Square(), Square(Point(1,1),2))
    val s3 = s1.move(Point(1,-5))
    val s4 = s3.scale(math.Pi)
    println(s4)
  }

}
