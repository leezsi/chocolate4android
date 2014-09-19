package ar.edu.unq.chocolate.core.components;

import android.graphics.Canvas
import ar.edu.unq.chocolate.core.appearances.Appearance
import ar.edu.unq.chocolate.core.dimensions.Bounded
import ar.edu.unq.chocolate.core.dimensions.Positioned
import ar.edu.unq.chocolate.core.reactions.events.RenderRequired
import ar.edu.unq.chocolate.core.reactions.events.Update

trait Visible extends GameComponent with Bounded with Positioned {

	def appearance : Appearance

	override def left = translation.x + appearance.left
	override def top = translation.y + appearance.top
	override def width = appearance.width
	override def height = appearance.height

	in {
		case Update(delta) ⇒ appearance.update(delta)
		case RenderRequired(graphics) ⇒ render(graphics)
	}

	def render(graphics : Canvas) = appearance.renderAt(translation, graphics)
}