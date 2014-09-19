package ar.edu.unq.chocolate.utils.commoncomponents.ui

import ar.edu.unq.chocolate.core.appearances.Appearance
import ar.edu.unq.chocolate.core.collisions.NoBoundingBox
import ar.edu.unq.chocolate.core.reactions.annotations.OnCustomEvent
import UIComponent._
import ar.edu.unq.chocolate.core.components.Visible
import ar.edu.unq.chocolate.core.components.Collisionable

object UIComponent {
	final val MAIN_ACTION = "UI Main Action"
}

trait UIComponent extends Visible with Collisionable {
	@OnCustomEvent(MAIN_ACTION)
	def reactToMainAction = performMainAction

	def performMainAction
}