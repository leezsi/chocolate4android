package ar.edu.unq.chocolate.core.reactions.events

import ar.edu.unq.chocolate.core.reactions.ContinuableEvent
import android.view.MotionEvent
import ar.edu.unq.chocolate.core.reactions.EventSignature
import ar.edu.unq.chocolate.core.dimensions.Vector
trait TouchEvent extends SimpleEvent {
  var pointerId: Int
  var position:Vector
  
  override def mainSignature = EventSignature(getClass, pointerId)

}

case class PointerDown(var pointerId: Int,var position : Vector) extends TouchEvent

case class PointerUp(var pointerId: Int,var position : Vector) extends TouchEvent

case class PointerMove(var pointerId: Int,var position : Vector) extends TouchEvent

case class PointerHold(var pointerId: Int,var position : Vector, delta: Double) extends TouchEvent with ContinuableEvent {
  def this(pointerId: Int,position : Vector) = this(pointerId,position, 0)

  def next(d: Double) = copy(delta = d)
}
