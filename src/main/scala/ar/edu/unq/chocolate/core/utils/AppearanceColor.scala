package ar.edu.unq.chocolate.core.utils

import android.graphics.Color.{ red, green, blue, alpha }
import android.graphics.Color

class AppearanceColor(val alpha: Int, val red: Int, val green: Int, val blue: Int) {

  def this(red: Int, green: Int, blue: Int) = this(0, red, green, blue)

  def this(argb: Int) = this(red(argb), green(argb), blue(argb), alpha(argb))

  def argb() = Color.argb(alpha, red, green, blue)

  implicit def color_to_int(color: AppearanceColor): Int = color.argb
}

object AppearanceColor extends AppearanceColor(0) {

  val BLACK = new AppearanceColor(0xFF000000)
  val DKGRAY = new AppearanceColor(0xFF444444)
  val GRAY = new AppearanceColor(0xFF888888)
  val LTGRAY = new AppearanceColor(0xFFCCCCCC)
  val WHITE = new AppearanceColor(0xFFFFFFFF)
  val RED = new AppearanceColor(0xFFFF0000)
  val GREEN = new AppearanceColor(0xFF00FF00)
  val BLUE = new AppearanceColor(0xFF0000FF)
  val YELLOW = new AppearanceColor(0xFFFFFF00)
  val CYAN = new AppearanceColor(0xFF00FFFF)
  val MAGENTA = new AppearanceColor(0xFFFF00FF)
  val TRANSPARENT = new AppearanceColor(0)

}

