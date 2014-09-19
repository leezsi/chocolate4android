package ar.edu.unq.chocolate.core

import scala.Int.int2double
import scala.collection.mutable.Seq
import ar.edu.unq.chocolate.core.components.GameComponent
import ar.edu.unq.chocolate.core.dimensions.Positioned
import ar.edu.unq.chocolate.core.dimensions.Vector
import ar.edu.unq.chocolate.core.dimensions.Vector.ORIGIN
import ar.edu.unq.chocolate.core.dimensions.Vector.touple_to_vector
import ar.edu.unq.chocolate.core.reactions.events.RenderRequired
import ar.edu.unq.chocolate.core.utils.Implicits.double_to_int
import ar.edu.unq.chocolate.core.utils.Implicits.double_to_float
import android.graphics.Canvas
import android.graphics.Bitmap
import android.graphics.Matrix

class DefaultCamera(scene: GameScene) extends Camera {
  val zoom: Vector = (1, 1)
  val screenSize: Vector = scene.game.displaySize
  val screenPosition = ORIGIN
  val z = Double.MaxValue
}

class TargetedCamera(var screenPosition: Vector, var screenSize: Vector)(var z: Double)(var zoom: Vector = (1, 1))(var target: Positioned) extends Camera {
  override def translation = target.translation - screenSize / 2
}

trait Camera extends Positioned {
  def zoom: Vector
  def screenSize: Vector
  def screenPosition: Vector
  def z: Double

  def apply(graphics: Canvas) = {
    val bitmap = Bitmap.createBitmap(screenSize.x - screenPosition.x, screenSize.y - screenPosition.y, Bitmap.Config.ARGB_8888)

    val answer = new Canvas(bitmap)

    val t = translation

    answer.scale(zoom.x, zoom.y)
    answer.translate(-translation.x, -translation.y)

    answer
  }

  def shoot(components: Seq[GameComponent], graphics: Canvas) {
    val g = this(graphics)
    components filter (_.z < z) foreach { _.reactTo(new RenderRequired(g)) }
  }
}