package Lab7.turtlerace
  import java.awt.Color
  import Lab6.turtlegraphics.Turtle
  import cslib.window.SimpleWindow

  class ColorTurtle (window: SimpleWindow,
                     private var color: Color

                    ) extends Turtle(window) {


    override def forward (length: Double): Unit = {
      val c = window.getLineColor
      window.setLineColor(color)
      super.forward(length)
      window.setLineColor(c)
    }
  }

