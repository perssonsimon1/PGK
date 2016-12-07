package Lab13.img

import Lab13.img.Filters.{BlueFilter, GreyScaleFilter, InvertFilter, RedFilter}
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
      new GreyScaleFilter("Gråskale filter",1)
    )
    val fc = new FilterChooser(filters)
    val fl = fc.chooseFilter()
    val sw = new SimpleWindow(img.width, img.height, "ImageFilter")
    fl.applyFilter(img, sw)
  }
}
