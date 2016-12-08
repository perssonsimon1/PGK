package Lab13.img

import cslib.images.ImageFilter
import cslib.window.SimpleWindow
import scala.collection.mutable.ArrayBuffer

/**
  * Created by simonpersson on 2016-12-06.
  */
class  FilterList {
  var filters = ArrayBuffer[(ImageFilter, Array[Double])]()

  def addFilter(filter: ImageFilter, args: Array[Double] = Array(0.0)): Unit = {
    filters += Tuple2(filter, args)
  }

  def applyFilter(image: Image, sw: SimpleWindow): Unit = {
    val img: Image = image
    val temp: Array[Double] = Array[Double](0.0)
    for(f <- filters.indices){
      img.updateImage(filters(f)._1.apply(img.getColorMatrix, filters(f)._2))
    }

    sw.drawImage(img.image)
  }
}
