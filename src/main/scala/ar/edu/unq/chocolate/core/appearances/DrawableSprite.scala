package ar.edu.unq.chocolate.core.appearances;

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import ar.edu.unq.chocolate.core.dimensions.Vector.ORIGIN
import ar.edu.unq.chocolate.core.dimensions.Vector.touple_to_vector
import ar.edu.unq.chocolate.core.utils.Implicits.double_to_float
import ar.edu.unq.chocolate.core.utils.Implicits.double_to_int
import android.graphics.drawable.Drawable

class DrawableSprite(var image: Drawable) extends ComplexAppearance {
  val bounds = image.getBounds()
  def width = bounds width
  def height = bounds height
  private val matrix = new Matrix

  // ****************************************************************
  // ** QUERIES
  // ****************************************************************

  def ::(target: Sprite) = {
    //    val nleft = left min target.left
    //    val nright = right max target.right
    //    val ntop = top min target.top
    //    val nbottom = bottom max target.bottom
    //    val nwidth = nright - nleft
    //    val nheight = nbottom - ntop
    //
    //    
    //    
    //    val img = Bitmap.createBitmap(nwidth, nheight, image.getConfig())
    //    canvas.setBitmap(img)
    //    canvas.translate(-nleft, -ntop)
    //    target renderAt (ORIGIN, canvas)
    //    val answer = new Sprite(img)
    //    answer.translation = (nleft, ntop)
    //    answer
  }

  //  def transformedImage(image: Bitmap)(matrix: Matrix) = {
  //    //    val answer = Bitmap.createBitmap(image getWidth (), image getHeight (), image.getConfig())
  //    canvas.setBitmap(image)
  //    canvas.drawBitmap(image, matrix, new Paint)
  //    //    answer
  //  }
  // ****************************************************************
  // ** TRANSFORMATIONS
  // ****************************************************************

  def scale(hRatio: Double = 1)(vRatio: Double = 1) = {
    matrix preScale (hRatio, vRatio)
    this
  }

  def rotate(radians: Double): this.type = {
    matrix preRotate (radians)
    this
  }

  def flipHorizontally = {
    scale(-1)()
    matrix preTranslate (width, 0)
  }
  def flipVertically = {
    scale()(-1)
    matrix preTranslate (0, height)
  }
  def crop(x: Double, y: Double, width: Double, height: Double) = {
    //    Bitmap.createBitmap(image, x, y, width, height)
    this
  }

  def repeat(horizontalRepetitions: Double, verticalRepetitions: Double) = {
    //    val horizontalIterations = horizontalRepetitions.ceil.toInt
    //    val verticalIterations = verticalRepetitions.ceil.toInt
    //    
    //    val newImage = Bitmap.createBitmap((width * horizontalRepetitions).toInt,
    //      (height * verticalRepetitions).toInt, image.getConfig())
    //    canvas.setBitmap(newImage)
    //    for {
    //      i ← 0 until horizontalIterations
    //      j ← 0 until verticalIterations
    //    } canvas drawBitmap (image, i * width.toInt, j * height.toInt, new Paint)
    //    image.recycle()
    //    image = newImage
  }

  // ****************************************************************
  // ** GAME LOOP OPERATIONS
  // ****************************************************************

  def update(delta: Double) = {}

  protected def doRenderAt(x: Double, y: Double, graphics: Canvas) = {
    graphics.setMatrix(matrix)
    image.setBounds(x, y, x + width, y + height)
    image.draw(graphics)
  }
}