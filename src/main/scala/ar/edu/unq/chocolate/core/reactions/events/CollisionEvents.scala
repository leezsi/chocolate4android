package ar.edu.unq.chocolate.core.reactions.events

import ar.edu.unq.chocolate.core.reactions.EventSignature
import ar.edu.unq.chocolate.core.components.Collisionable

case class Collision(collidedComponent : Collisionable) extends SimpleEvent

case class CollisionEnd(collidedComponent : Collisionable) extends SimpleEvent