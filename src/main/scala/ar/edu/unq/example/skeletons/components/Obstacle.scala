package ar.edu.unq.example.skeletons.components

import ar.edu.unq.chocolate.core.components.Collisionable
import ar.edu.unq.chocolate.core.appearances.Appearance
import ar.edu.unq.chocolate.core.collisions.BoundingBox
import ar.edu.unq.chocolate.core.reactions.annotations.collision.OnCollision
import ar.edu.unq.chocolate.core.reactions.events.Collision
import ar.edu.unq.chocolate.core.components.Visible

class Obstacle(val appearance : Appearance, val boundingBox : BoundingBox)(obstacleTop : Visible, overlap : Double) extends Visible with Collisionable {

	alignHorizontally(_.left)(obstacleTop.left)
	alignVertically(_.top)(obstacleTop.bottom - overlap)

	z = 1

	def beHit {
		destroy
		obstacleTop.destroy
	}

	// ****************************************************************
	// ** TRIGGERS
	// ****************************************************************

	in { case Collision(c : Firebolt) ⇒ beHit }
}