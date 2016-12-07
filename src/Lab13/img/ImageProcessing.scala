package Lab13.img

import java.util.Scanner

import Lab13.img.Filters._
import cslib.images.ImageFilter
import cslib.window.SimpleWindow

/**
  * Created by simonpersson on 2016-12-06.
  */
object ImageProcessing {
  def main(args: Array[String]): Unit = {
    val img = new Image(ImageUI.getImage)
    val filters: Array[ImageFilter] = Array(
      new BlueFilter("Blått filter",0),
      new RedFilter("Rött filter",0),
      new InvertFilter("Invert filter",0),
      new GreyScaleFilter("Gråskale filter",0),
      new XORCryptFilter("XORCrypt filter", 1),
      new GaussFilter("Gauss filter", 1)
    )
    val fc = new FilterChooser(filters)
    val fl = fc.chooseFilter()
    val sw = new SimpleWindow(img.width, img.height, "ImageFilter")
    fl.applyFilter(img, sw)

    val scanner = new Scanner(System.in)
    println("Välja ny bild? (Y/n)")
    scanner.nextLine().toLowerCase match {
      case "y" => main(Array(""))
      case "n" => System.exit(0)
    }
  }
}
