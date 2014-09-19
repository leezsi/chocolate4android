package ar.edu.unq.chocolate.core.appearances

import android.graphics.Paint
import ar.edu.unq.chocolate.core.utils.AppearanceColor
import android.graphics.Rect
import ar.edu.unq.chocolate.core.utils.Implicits.double_to_int

trait AppearanceDefaults {

  val color: AppearanceColor
  val defaultPaint = new Paint

  defaultPaint setColor color
  defaultPaint setStyle(Paint.Style.FILL_AND_STROKE)
  
  
  implicit def tuple4_to_Rect(t:(Double,Double,Double,Double)):Rect=new Rect(t._1,t._2,t._1+t._3,t._2+t._4)
}