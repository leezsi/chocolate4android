package ar.edu.unq.chocolate.core.appearances;

import android.graphics.Canvas


class Invisible extends Appearance {
	def width = 0
	def height = 0

	def update(delta : Double) = {}
	protected def doRenderAt(x : Double, y : Double, graphics : Canvas) = {}
}