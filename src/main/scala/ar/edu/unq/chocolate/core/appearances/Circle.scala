package ar.edu.unq.chocolate.core.appearances

import ar.edu.unq.chocolate.core.utils.Implicits._
import ar.edu.unq.chocolate.core.utils.AppearanceColor
import android.graphics.Canvas
import android.graphics.Paint

class Circle(val radius: Double, filled: Boolean = true)(val color: AppearanceColor) extends Appearance with AppearanceDefaults {
  val width = 2 * radius
  val height = 2 * radius
  translation = (-radius, -radius)

  def update(delta: Double) {}
  def doRenderAt(x: Double, y: Double, graphics: Canvas) {
    if (!filled) {
      defaultPaint setStyle Paint.Style.STROKE
    }
    graphics.drawCircle(x, y, radius, defaultPaint)

  }
}