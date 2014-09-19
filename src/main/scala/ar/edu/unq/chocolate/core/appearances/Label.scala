package ar.edu.unq.chocolate.core.appearances

import java.awt.Graphics2D
import Label._
import ar.edu.unq.chocolate.core.utils.Implicits._
import android.graphics.Canvas
import android.graphics.Paint
import ar.edu.unq.chocolate.core.utils.AppearanceColor
import ar.edu.unq.chocolate.core.utils.AppearanceColor.BLACK
import ar.edu.unq.chocolate.core.utils.AppearanceFont

object Label {
  val DEFAULT_FONT = AppearanceFont.MONOSPACE
  val DEFAULT_FONT_SIZE = 15
  val DEFAULT_COLOR = BLACK
}

class Label(var font: AppearanceFont = DEFAULT_FONT)(var color: AppearanceColor = DEFAULT_COLOR)(var fontSize:Int=DEFAULT_FONT_SIZE )(someTextLines: String = "") extends Appearance {

  val paint = new Paint
  paint setTypeface font
  paint setColor color
  paint setTextSize fontSize 
  
  var textLines = someTextLines.lines.toIndexedSeq
  def width = textLines.map(paint.measureText(_)).max
  def height = linesCount * lineHeight

  // ****************************************************************
  // ** TRANSFORMATIONS
  // ****************************************************************

  def scale(hRatio: Double)(vRatio: Double) = null //TODO
  def crop(x: Double, y: Double, width: Double, height: Double) = null // TODO
  def flipHorizontally {} // TODO
  def flipVertically {} // TODO
  def repeat(horizontalRepetitions: Double, verticalRepetitions: Double) {} //TODO
  def rotate(angle: Double) {} //TODO

  // ****************************************************************
  // ** QUERIES
  // ****************************************************************

  def linesCount = textLines.size
  def lineHeight = {
    val metrics = paint.getFontMetrics()
    (metrics.top - metrics.bottom) * 1.05
  }

  // ****************************************************************
  // ** OPERATIONS
  // ****************************************************************

  def text: String = textLines.mkString("\n")
  def text_=(text: String): Unit = textLines = text.lines.toIndexedSeq

  // ****************************************************************
  // ** GAME LOOP OPERATIONS
  // ****************************************************************

  override def update(delta: Double) = {}

  protected override def doRenderAt(x: Double, y: Double, graphics: Canvas) = {
  
    for (index ← 0 until textLines.size) {
      graphics.drawText(textLines(index), x + translation.x, y + translation.y + lineHeight * (index + 1), paint)
    }
  }
}