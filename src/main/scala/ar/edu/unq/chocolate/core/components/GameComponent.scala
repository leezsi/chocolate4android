package ar.edu.unq.chocolate.core.components

import ar.edu.unq.chocolate.core.utils.SimpleToString
import ar.edu.unq.chocolate.core.GameScene
import ar.edu.unq.chocolate.core.reactions.ReactionRegistry
import ar.edu.unq.chocolate.core.reactions.events.Destroyed
import ar.edu.unq.chocolate.core.reactions.events.GameEvent

trait GameComponent extends SimpleToString {

	var z : Double = 0 // TODO: DOCUMENTAR QUE ES EL Z.
	var destroyPending = false

	var _scene : GameScene = null
	def scene[T <: GameScene] : T = _scene.asInstanceOf[T]
	def scene_=(value : GameScene) = _scene = value

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	def reactions = {
		if (!ReactionRegistry.hasBeenInitializedFromAnnotations(getClass)) ReactionRegistry.initializeFromAnnotations(getClass)

		ReactionRegistry(this)
	}

	def game = scene.game

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	def destroy {
		destroyPending = true
		scene.pushEvent(Destroyed(this))
	}

	def reactTo(event : GameEvent) = for {
		reaction ← reactions
		if (reaction.isDefinedAt((event, this)))
	} reaction(event, this)

	def in = ReactionRegistry.register(this)(_)
}