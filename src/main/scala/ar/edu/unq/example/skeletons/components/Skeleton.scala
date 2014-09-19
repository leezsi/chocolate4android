package ar.edu.unq.example.skeletons.components

import ar.edu.unq.chocolate.core.components.Visible
import ar.edu.unq.chocolate.core.components.Collisionable
import ar.edu.unq.chocolate.core.appearances.Appearance
import ar.edu.unq.chocolate.core.components.GameComponent
import ar.edu.unq.chocolate.core.dimensions.Bounded
import ar.edu.unq.chocolate.core.collisions.RectangularBoundingBox
import ar.edu.unq.chocolate.core.reactions.annotations.scene.OnUpdate
import ar.edu.unq.chocolate.core.reactions.events.Update
import ar.edu.unq.chocolate.core.dimensions.Vector._
import ar.edu.unq.chocolate.core.reactions.annotations.collision.OnCollision
import ar.edu.unq.chocolate.core.reactions.events.Collision
import ar.edu.unq.chocolate.core.reactions.events.Collision

class Skeleton(var appearance : Appearance, target : GameComponent with Bounded) extends Visible with Collisionable {

	val MOVE_FACTOR = 100

	val boundingBox = new RectangularBoundingBox(44, 62)
	boundingBox.translation = (10, 2)

	translation = (30, 30)

	override def clone = {
		val answer = super.clone
		answer.appearance = appearance.clone
		answer
	}

	def advance(delta : Double) {
		val movement = MOVE_FACTOR * delta

		this.move((target.center - center, target.middle - middle).asVersor * movement)
	}

	def adjustPositionTo(c : Collisionable) {
		val delta = boundingBox.collisionCorrectionVectorAgainst(translation, c)
		move(delta)
	}

	// ****************************************************************
	// ** TRIGGERS
	// ****************************************************************

	in {
		case Update(delta) ⇒ advance(delta)

		case Collision(c : Firebolt) ⇒ destroy

		case Collision(c) ⇒ adjustPositionTo(c)
	}
}