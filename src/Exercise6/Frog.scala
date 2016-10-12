package Exercise6

import scala.util.Random

/**
  * Created by simonpersson on 2016-10-04.
  */
class Frog private (initX: Int = 0, initY: Int = 0) {
  private var _x = initX
  private var _y = initY
  private var _distJumped: Double = 0


  def jump(dx: Int, dy: Int): Unit = {
    _x += dx
    _y += dy
    _distJumped += math.hypot(dx,dy)
  }
  def randomJump: Unit = {
    val rnd = Random
    val xtmp = rnd.nextInt(10) + 1
    val ytmp = rnd.nextInt(10) + 1
    _x += xtmp
    _y += ytmp
    _distJumped += math.hypot(xtmp,ytmp)
  }

  def x: Int = _x
  def y: Int = _y

  def x_= (value: Int): Unit = {
    _distJumped += math.abs(_x - value)
    _x = value
  }

  def y_= (value: Int): Unit = {
    _distJumped += math.abs(_y - value)
    _y = value
  }



  def distToStart: Double = math.hypot(_x,_y)
  def distJumped: Double = _distJumped
  def distTo (dest: Frog): Double = {
    math.hypot(_x - dest._x, _y - dest.y)
  }

  override def toString: String = s"Frogs location is (${_x}, ${_y}) and the distance is ${_distJumped}"

}

object Frog {
  def spawn(): Frog = new Frog()
}
