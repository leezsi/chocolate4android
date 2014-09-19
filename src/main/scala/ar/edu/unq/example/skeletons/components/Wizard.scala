package ar.edu.unq.example.skeletons.components

import scala.Int.int2double
import ar.edu.unq.chocolate.core.appearances.Appearance
import ar.edu.unq.chocolate.core.collisions.RectangularBoundingBox
import ar.edu.unq.chocolate.core.components.Collisionable
import ar.edu.unq.chocolate.core.components.Visible
import ar.edu.unq.chocolate.core.dimensions.Vector.touple_to_vector
import ar.edu.unq.chocolate.core.reactions.annotations.collision.OnCollision
import ar.edu.unq.chocolate.core.reactions.events.Collision
import ar.edu.unq.chocolate.core.reactions.events.Collision
import ar.edu.unq.chocolate.core.reactions.events.Update
import ar.edu.unq.chocolate.core.reactions.events.PointerMove
import ar.edu.unq.chocolate.core.dimensions.Vector

class Wizard(val appearance: Appearance)(shootFactory: Unit ⇒ Firebolt) extends Visible with Collisionable {
  val MOVE_FACTOR = 200
  var lastPosition: Vector = (0, 0)
  val boundingBox = new RectangularBoundingBox(32, 38)
  boundingBox.translation = (6, 34)

  z = 10

  def shoot = {
    val p = shootFactory()
    p.align(_.center, _.middle)(center, middle)
    scene.addComponent(p)
  }

  def adjustPositionTo(c: Collisionable) {
    val delta = boundingBox.collisionCorrectionVectorAgainst(translation, c)

    move(delta)
  }

  // ****************************************************************
  // ** TRIGGERS
  // ****************************************************************

  in {
    case PointerMove(pointerId, position) => move(lastPosition - position)
    //    case KeyHold(UP, delta) ⇒ move(0, -delta * MOVE_FACTOR)
    //    case KeyHold(DOWN, delta) ⇒ move(0, delta * MOVE_FACTOR)
    //    case KeyHold(LEFT, delta) ⇒ move(-delta * MOVE_FACTOR, 0)
    //    case KeyHold(RIGHT, delta) ⇒ move(delta * MOVE_FACTOR, 0)
    //
    //    case KeyPressed(SPACE) ⇒ shoot

    case Collision(c: Firebolt) ⇒
    case Collision(c: Skeleton) ⇒ destroy
    case Collision(c) ⇒ adjustPositionTo(c)
  }
}