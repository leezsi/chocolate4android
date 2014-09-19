package ar.edu.unq.chocolate.core.appearances;

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import ar.edu.unq.chocolate.core.dimensions.Vector.ORIGIN
import ar.edu.unq.chocolate.core.dimensions.Vector.touple_to_vector
import ar.edu.unq.chocolate.core.utils.Implicits.double_to_float
import ar.edu.unq.chocolate.core.utils.Implicits.double_to_int

class Sprite(var image: Bitmap) extends ComplexAppearance {

  def width = image.getWidth()
  def height = image.getHeight()
  private val canvas=new Canvas

  // ****************************************************************
  // ** QUERIES
  // ****************************************************************

  def ::(target: Sprite) = {
    val nleft = left min target.left
    val nright = right max target.right
    val ntop = top min target.top
    val nbottom = bottom max target.bottom
    val nwidth = nright - nleft
    val nheight = nbottom - ntop

    val img = Bitmap.createBitmap(nwidth, nheight, image.getConfig())
    canvas.setBitmap(img)
    canvas.translate(-nleft, -ntop)
    target renderAt (ORIGIN, canvas)
    val answer = new Sprite(img)
    answer.translation = (nleft, ntop)
    answer
  }

  def transformedImage(image: Bitmap)(matrix: Matrix) = {
//    val answer = Bitmap.createBitmap(image getWidth (), image getHeight (), image.getConfig())
    canvas.setBitmap(image)
    canvas.drawBitmap(image, matrix, new Paint)
//    answer
  }
  // ****************************************************************
  // ** TRANSFORMATIONS
  // ****************************************************************

 
  def scale(hRatio: Double = 1)(vRatio: Double = 1) = {
    val matrix = new Matrix
    matrix setScale (hRatio, vRatio)
    transformedImage(image)(matrix)
    this
  }

  def rotate(radians: Double): this.type = {
    val matrix = new Matrix
    matrix setRotate (radians)
    transformedImage (image)(matrix)
    this
  }

  def flipHorizontally = {
    val matrix = new Matrix
    matrix setTranslate (image getWidth (), 0)
    scale(-1)().transformedImage(image)(matrix)
  }
  def flipVertically = {
    val matrix = new Matrix
    matrix setTranslate (0, image getHeight ())
    scale()(-1).transformedImage(image)(matrix)
  }
  def crop(x: Double, y: Double, width: Double, height: Double) = {
    Bitmap.createBitmap(image, x, y, width, height)
    this
  }

  def repeat(horizontalRepetitions: Double, verticalRepetitions: Double) = {
    val horizontalIterations = horizontalRepetitions.ceil.toInt
    val verticalIterations = verticalRepetitions.ceil.toInt
    val newImage = Bitmap.createBitmap((width * horizontalRepetitions).toInt,
      (height * verticalRepetitions).toInt, image.getConfig())
    canvas.setBitmap(newImage)
    for {
      i ← 0 until horizontalIterations
      j ← 0 until verticalIterations
    } canvas drawBitmap (image, i * width.toInt, j * height.toInt, new Paint)
    image.recycle()
    image=newImage
  }

  // ****************************************************************
  // ** GAME LOOP OPERATIONS
  // ****************************************************************

  def update(delta: Double) = {}

  protected def doRenderAt(x: Double, y: Double, graphics: Canvas) = graphics.drawBitmap(image, x, y, new Paint)
}