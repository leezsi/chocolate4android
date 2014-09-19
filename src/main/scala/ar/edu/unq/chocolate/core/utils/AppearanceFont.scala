package ar.edu.unq.chocolate.core.utils

import android.graphics.Typeface
import android.content.Context
import android.text.style.TypefaceSpan

class AppearanceFont(private val family: String, private val style: Int, private val typeFace: Typeface) {
  def this(family: String, style: Int) = this(family, style, null)

  def this(typeFace: Typeface) = this(null, 0, typeFace)

  val _typeFace = if (typeFace == null) Typeface.create(family, style) else typeFace

  def bold = new AppearanceFont(family, Typeface.BOLD)
  def italic = new AppearanceFont(family, Typeface.ITALIC)
  def boldItalic = new AppearanceFont(family, Typeface.BOLD_ITALIC)
  
  implicit def font_to_typeface(font: AppearanceFont): Typeface = this.typeFace
}

object AppearanceFont extends AppearanceFont("monospace", 0) {
  def SANS_SERIF = new AppearanceFont(Typeface.SANS_SERIF)
  def SERIF = new AppearanceFont(Typeface.SERIF)
  def MONOSPACE = new AppearanceFont(Typeface.MONOSPACE)
  def fromAssets(context: Context, path: String) = new AppearanceFont(Typeface.createFromAsset(context.getAssets(), path))
}