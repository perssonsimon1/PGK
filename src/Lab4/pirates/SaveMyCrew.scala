package Lab4.pirates

/**
  * Created by simonpersson on 22/09/16.
  */

import scala.io.StdIn.readLine

object SaveMyCrew {
  def main(args: Array[String]): Unit = {

    val filename = if (!args.isEmpty) args(0) else "util/crew.txt"
    saveCrew(filename, getNewCrew(5))
    readCrew(filename)

  }

  def saveCrew(filename: String, crew: Vector[CrewMember] = null): Unit = {

    if (crew != null) FileUtil.save(crew.mkString("\n"), filename)

  }

  def readCrew(filename: String): Unit = {
    val fileLines = FileUtil.readLines(filename)
    val tempCrew: Vector[IndexedSeq[String]] = fileLines.map(line => line.replaceAll(",","").split("\\s+").toIndexedSeq)
    val crew = tempCrew.map(member => CrewMember(member(0),member(1),member(2)))
    print(crew)

  }

  def getNewCrew(members: Int): Vector[CrewMember] = {
    val crew = for(i <- 1 to members) yield {

      val tempFirstname = readLine("First name: ")
      val tempLastname = readLine("Last name: ")
      val tempPost = readLine("Post: ")

      if(tempFirstname.isEmpty || tempLastname.isEmpty || tempPost.isEmpty) {
        println("Error: Please use all fields")
        getNewCrew(members)
      }
      if(tempFirstname.contains(' ') || tempLastname.contains(' ') || tempPost.contains(' ')) {
        println("Error: Please do not use whitespaces")
        getNewCrew(members)
      }



      println("---------------------------------------")

      CrewMember(tempFirstname,tempLastname,tempPost)

    }
    crew.toVector
  }

}
