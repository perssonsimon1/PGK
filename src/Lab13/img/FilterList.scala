package Lab13.img

import cslib.images.ImageFilter
import cslib.window.SimpleWindow
import scala.collection.mutable.ArrayBuffer

class  FilterList {
  var filters = ArrayBuffer[(ImageFilter, Array[Double])]()

  /**
    *
    * @param filter   A filter to be added to the FilterList
    * @param args     Extra arguments if required by filter
    */
  def addFilter(filter: ImageFilter, args: Array[Double] = Array(0.0)): Unit = {
    filters += Tuple2(filter, args)
  }

  /**
    *
    * @param image    The image to be filtered
    * @param sw       A window to display the final image in
    */
  def applyFilter(image: Image, sw: SimpleWindow): Unit = {
    val img: Image = image
    val temp: Array[Double] = Array[Double](0.0)
    for(f <- filters.indices){
      img.updateImage(filters(f)._1.apply(img.getColorMatrix, filters(f)._2))
    }

    sw.drawImage(img.image)
  }
}
