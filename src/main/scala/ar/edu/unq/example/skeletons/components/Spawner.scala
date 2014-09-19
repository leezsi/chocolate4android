package ar.edu.unq.example.skeletons.components

import ar.edu.unq.chocolate.core.components.GameComponent
import ar.edu.unq.chocolate.core.reactions.annotations.scene.OnUpdate
import ar.edu.unq.chocolate.core.reactions.events.Update
import ar.edu.unq.chocolate.core.utils.Cloneable

class Spawner(frequency : Double)(factory : Unit ⇒ GameComponent) extends GameComponent {

	var timeToNextSpam = frequency

	// ****************************************************************
	// ** TRIGGERS
	// ****************************************************************

	@OnUpdate
	def updateTimeToNextSpam(event : Update) {
		timeToNextSpam -= event.delta

		if (timeToNextSpam <= 0) {
			scene.addComponent(factory())
			timeToNextSpam += frequency
		}
	}

}