package Lab13.img

import java.util.Scanner

import cslib.images.ImageFilter

/**
  * Created by simonpersson on 2016-12-06.
  */
class FilterChooser(filters: Array[ImageFilter]) {

  def chooseFilter(): FilterList = {
    val out: FilterList = new FilterList()

    val scanner: Scanner = new Scanner(System.in)

    var choosing = true

    val optOut = filters.length.toString

    while(choosing) {

      for(f <- filters.indices) {
        var postfix = ""
        if(filters(f).getNumberOfArguments > 0) postfix = s"(requires ${filters(f).getNumberOfArguments} arguments)"

        println(s"$f. för ${filters(f).getName} $postfix")
      }
      println(s"${filters.length}. om du inte vill använda fler filter ")

      scanner.nextLine().split(' ').toVector match {
        case Vector(`optOut`) => choosing = false
        case option +: args if (option.union(args).mkString(" ").matches("([\\d](\\p{Space})?)+")) => {
          if (option.toInt < filters.length) {
            if (args.length == filters(option.toInt).getNumberOfArguments) {
              out.addFilter(filters(option.toInt), args.map(x => x.toDouble).toArray)
            } else println("Ogiltig inmatning, försök igen!")
          }
          else println("Ogiltig inmatning, försök igen!")
        }
        case Vector(_*) => println("Ogiltig inmatning, försök igen!")
      }
    }
      out
  }

}
