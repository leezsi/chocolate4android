package ar.edu.unq.chocolate.core.loaders

import android.content.Context
import android.graphics.BitmapFactory
import ar.edu.unq.chocolate.core.appearances.Sprite
import ar.edu.unq.chocolate.core.Game
import android.util.Log
import android.graphics.Bitmap
import ar.edu.unq.chocolate.core.appearances.DrawableSprite

case class AndroidResourceLoader(val context: Context) {

  def loadSprite2(id: Int) = {
    val options = new BitmapFactory.Options
    options.inSampleSize=2
    options.inMutable=true
    val bitmap = BitmapFactory.decodeResource(context.getResources(), id,options)
    new Sprite(bitmap)
  }

  def loadSprite(id: Int) = {
    var image=context.getResources().getDrawable(id)
    new DrawableSprite(image)
  }
  
}

