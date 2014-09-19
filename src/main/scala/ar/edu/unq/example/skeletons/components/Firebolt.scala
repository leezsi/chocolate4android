package ar.edu.unq.example.skeletons.components

import scala.Int.int2double
import ar.edu.unq.chocolate.core.components.LimitedLifeSpanComponent
import ar.edu.unq.chocolate.core.components.Visible
import ar.edu.unq.chocolate.core.dimensions.Vector.touple_to_vector
import ar.edu.unq.chocolate.core.reactions.events.Collision
import ar.edu.unq.chocolate.core.collisions.CircularBoundingBox
import ar.edu.unq.chocolate.core.appearances.Appearance
import ar.edu.unq.chocolate.core.components.Collisionable
import ar.edu.unq.chocolate.core.appearances.Animation
import ar.edu.unq.chocolate.core.reactions.events.Update
import ar.edu.unq.chocolate.core.appearances.DrawableAnimation

class Firebolt(val appearance : Appearance, val explosionAnimation : DrawableAnimation) extends Visible with Collisionable {

	val MOVE_FACTOR = 300

	val boundingBox = new CircularBoundingBox(13)
	boundingBox.translation = (20, 20)

	def explode {
		val animation = explosionAnimation.clone
		val explosion = new LimitedLifeSpanComponent(animation, animation.duration)

		explosion.align(_.center, _.middle)(center, middle)

		scene.addComponent(explosion)

		destroy
	}

	// ****************************************************************
	// ** TRIGGERS
	// ****************************************************************

	in {
		case e : Update ⇒ move(0, -e.delta * MOVE_FACTOR)

		case Collision(w : Wizard) ⇒
		case Collision(_) ⇒ explode
	}
}