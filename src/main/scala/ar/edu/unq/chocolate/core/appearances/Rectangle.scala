package ar.edu.unq.chocolate.core.appearances

import ar.edu.unq.chocolate.core.utils.Implicits._
import ar.edu.unq.chocolate.core.dimensions.Vector
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import ar.edu.unq.chocolate.core.utils.AppearanceColor

class Rectangle(size: Vector)(val color: AppearanceColor) extends Appearance with AppearanceDefaults {
  val width = size.x
  val height = size.y

  def update(delta: Double) {}
  def doRenderAt(x: Double, y: Double, graphics: Canvas) {
    val rect = (x, y, width, height)
    graphics.drawRect(rect, defaultPaint)

  }
}