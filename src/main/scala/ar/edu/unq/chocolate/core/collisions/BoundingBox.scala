package ar.edu.unq.chocolate.core.collisions;

import ar.edu.unq.chocolate.core.dimensions.Vector
import ar.edu.unq.chocolate.core.utils.Cloneable
import ar.edu.unq.chocolate.core.dimensions.Bounded
import ar.edu.unq.chocolate.core.dimensions.Positioned
import ar.edu.unq.chocolate.core.components.Collisionable

abstract class BoundingBox extends Bounded with Positioned {

	def apply(translation : Vector)(target : BoundingBox, targetTranslation : Vector) =
		if (collidesWith(translation)(targetTranslation) isDefinedAt target) collidesWith(translation)(targetTranslation)(target)
		else target.collidesWith(targetTranslation)(translation)(this)

	def collisionCorrectionVectorAgainst(translation : Vector, target : Collisionable) : Vector = collisionCorrectionVectorAgainst(translation)(target.boundingBox, target.translation)
	def collisionCorrectionVectorAgainst(translation : Vector)(target : BoundingBox, targetTranslation : Vector) =
		if (collisionCorrectionVector(translation)(targetTranslation) isDefinedAt target) collisionCorrectionVector(translation)(targetTranslation)(target)
		else target.collisionCorrectionVector(targetTranslation)(translation)(this)

	protected def collidesWith(translation : Vector)(targetTranslation : Vector) : PartialFunction[BoundingBox, Boolean]

	protected def collisionCorrectionVector(translation : Vector)(targetTranslation : Vector) : PartialFunction[BoundingBox, Vector]
}