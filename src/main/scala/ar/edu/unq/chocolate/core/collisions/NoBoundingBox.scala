package ar.edu.unq.chocolate.core.collisions;

import java.awt.Graphics2D
import java.awt.geom.Point2D;
import ar.edu.unq.chocolate.core.dimensions.Vector
import ar.edu.unq.chocolate.core.dimensions.Vector._

case object NoBoundingBox extends BoundingBox {
	def left = 0
	def top = 0
	def width = 0
	def height = 0

	protected def collidesWith(translation : Vector)(targetTranslation : Vector) = {
		case _ ⇒ false
	}

	def collisionCorrectionVector(translation : Vector)(targetTranslation : Vector) = {
		case _ ⇒ ORIGIN
	}
}