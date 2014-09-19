package ar.edu.unq.chocolate.core.components;

import ar.edu.unq.chocolate.core.dimensions.Vector
import ar.edu.unq.chocolate.core.appearances.Appearance
import ar.edu.unq.chocolate.core.reactions.annotations.scene.OnUpdate
import ar.edu.unq.chocolate.core.reactions.events.Update

class LimitedLifeSpanComponent(val appearance : Appearance, var lifeSpan : Double) extends Visible {

	// ****************************************************************
	// ** TRIGGERS
	// ****************************************************************

	@OnUpdate
	def updateLifeSpan(event : Update) = {
		lifeSpan = lifeSpan - event.delta
		if (lifeSpan <= 0) destroy
	}
}