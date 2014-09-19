package ar.edu.unq.chocolate.core.reactions.events;

import ar.edu.unq.chocolate.core.reactions.EventSignature;

case class CustomEvent(keys : Any*) extends SimpleEvent {

	override def mainSignature = EventSignature(getClass, keys : _*)

}