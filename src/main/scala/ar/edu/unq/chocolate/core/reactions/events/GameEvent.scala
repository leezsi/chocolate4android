package ar.edu.unq.chocolate.core.reactions.events

import java.util.Collection
import ar.edu.unq.chocolate.core.reactions.EventSignature
import ar.edu.unq.chocolate.core.utils.Cloneable

trait GameEvent extends Cloneable {
	def mainSignature : EventSignature

	def matchingSignatures : List[EventSignature]
}