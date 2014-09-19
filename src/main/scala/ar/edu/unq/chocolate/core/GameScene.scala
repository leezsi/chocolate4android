package ar.edu.unq.chocolate.core

import scala.collection.mutable.ArrayBuffer
import ar.edu.unq.chocolate.core.components.GameComponent
import android.graphics.Rect
import ar.edu.unq.chocolate.core.reactions.EventQueue
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import ar.edu.unq.chocolate.core.components.Collisionable
import scala.collection.mutable.ListBuffer
import ar.edu.unq.chocolate.core.reactions.events.GameEvent
import ar.edu.unq.chocolate.core.reactions.ContinuableEvent
import android.graphics.Canvas
import ar.edu.unq.chocolate.core.reactions.events.ComponentAdded
import ar.edu.unq.chocolate.core.reactions.events.ComponentRemoved
object GameScene {
	val DEFAULT_COLLISION_ZONE_SIZE = new Rect(0,30,0, 30)
}
class GameScene {
 var game : Game = null
	val components = new ArrayBuffer[GameComponent] //TODO: No sería mejor que esto fuera de otra clase?
	val eventQueue = new EventQueue
	var collisionZoneSize = GameScene.DEFAULT_COLLISION_ZONE_SIZE
	val collisionZones = new HashMap[(Int, Int), HashSet[Collisionable]]
	val cameras : ListBuffer[Camera] = ListBuffer()

	protected var nextTranslation : Option[GameScene] = None

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	def this(components : GameComponent*) = {
		this()
		components map addComponent
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	def componentCount = components.size

	def zFromComponentAt(index : Int) = components(index).z

	def indexToInsert(component : GameComponent) : Int = {
		var lowerIndex = 0
		var higherIndex = componentCount - 1
		var searchedZ = component.z

		if (components.isEmpty || searchedZ < zFromComponentAt(lowerIndex)) {
			return 0;
		}

		if (searchedZ >= zFromComponentAt(higherIndex)) {
			return componentCount
		}

		while (lowerIndex <= higherIndex) {
			val middleIndex = lowerIndex + higherIndex >>> 1
			val middleZ = zFromComponentAt(middleIndex)

			if (middleZ <= searchedZ) {
				lowerIndex = middleIndex + 1
			} else if (middleZ > searchedZ) {
				higherIndex = middleIndex - 1
			}
		}

		return lowerIndex
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	def pushEvent(event : GameEvent) = eventQueue pushEvent event

	def startPushingEvent(event : ContinuableEvent) = eventQueue startPushingEvent event

	def stopPushingEvent(event : ContinuableEvent) = eventQueue stopPushingEvent event

	def takeStep(graphics : Canvas) = {
		val events = eventQueue.takePendingEvents

		for (component ← components.clone)
			if (component.destroyPending) removeComponent(component)
			else events foreach (component.reactTo(_))

		if (cameras.isEmpty) cameras += new DefaultCamera(this)

		for (camera ← cameras) camera.shoot(components, graphics)

		nextTranslation match {
			case Some(s) ⇒ {
				game.currentScene = s

				eventQueue.reset

				nextTranslation = None
			}
			case None ⇒
		}
	}

	def pause = eventQueue.pause

	def translateTo(scene : GameScene) = nextTranslation = Some(scene)

	// ****************************************************************
	// ** ACCESS OPERATIONS
	// ****************************************************************

	def addComponent(component : GameComponent) = {
		components.insert(indexToInsert(component), component)
		component.scene = this
		pushEvent(new ComponentAdded(component))
	}

	def addComponents(components : GameComponent*) = components map addComponent

	protected def removeComponent(component : GameComponent) = {
		pushEvent(ComponentRemoved(component))
		components -= component
		component.scene = null
	}

	def addCamera(camera : Camera) = cameras += camera

	def removeCamera(camera : Camera) = cameras -= camera

	def addToCollisionZone(x : Int, y : Int, component : Collisionable) = collisionZones.getOrElseUpdate((x, y), new HashSet).add(component)

	def removeFromCollisionZone(x : Int, y : Int, component : Collisionable) = for (groupMembers ← collisionZones.get(x, y)) groupMembers.remove(component)
}