package ar.edu.unq.chocolate.utils.commoncomponents.ui

import ar.edu.unq.chocolate.core.appearances.Appearance
import ar.edu.unq.chocolate.core.collisions.BoundingBox
import ar.edu.unq.chocolate.core.dimensions.Vector
import ar.edu.unq.chocolate.core.collisions.RectangularBoundingBox

class Button(val appearance : Appearance)(position : Vector)(mainAction : UIComponent ⇒ Unit) extends UIComponent {

	translation = position
	val boundingBox = new RectangularBoundingBox(appearance.width, appearance.height)

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	override def performMainAction = mainAction(this)
}