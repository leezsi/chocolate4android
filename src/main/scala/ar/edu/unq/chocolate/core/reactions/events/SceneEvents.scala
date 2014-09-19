package ar.edu.unq.chocolate.core.reactions.events

import scala.collection.Map
import scala.collection.Set
import ar.edu.unq.chocolate.core.reactions.ContinuableEvent
import ar.edu.unq.chocolate.core.components.GameComponent
import android.graphics.Canvas

case class ComponentAdded(component : GameComponent) extends SimpleEvent
case class ComponentRemoved(component : GameComponent) extends SimpleEvent

case class SceneSetAsCurrent() extends SimpleEvent

case class RenderRequired(graphics : Canvas) extends SimpleEvent

case class Destroyed(component : GameComponent) extends SimpleEvent

case class Update(delta : Double) extends SimpleEvent with ContinuableEvent {
	def this() = this(0)
	def next(d : Double) = copy(delta = d)
}