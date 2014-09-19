package ar.edu.unq.chocolate.core.reactions

import ar.edu.unq.chocolate.core.dimensions.Vector._
import ar.edu.unq.chocolate.core.Game
import android.graphics.Point
import android.view.View.OnTouchListener
import android.view.MotionEvent
import android.view.MotionEvent._
import android.view.View
import ar.edu.unq.chocolate.core.GamePlayer
import scala.collection.mutable.Set
import ar.edu.unq.chocolate.core.reactions.events._
import ar.edu.unq.chocolate.core.dimensions.Vector
import scala.collection.mutable.HashMap

class EventAdapter(game: Game) extends OnTouchListener {

  def tupleToVector(implicit t: (Int, Int)): Vector = (t._1, t._2)

  def addListeners(gamePlayer: GamePlayer) {
    gamePlayer.setOnTouchListener(this)
  }

  @volatile var holdes = HashMap[Int, Int]()

  def onTouch(view: View, event: MotionEvent): Boolean = synchronized {
    val actionIndex = event.getActionIndex()
    val actionPointer = event.getPointerId(actionIndex)
    event.getActionMasked() match {
      case ACTION_DOWN | ACTION_POINTER_DOWN => actionDown(actionIndex, actionPointer, event)
      case ACTION_UP | ACTION_POINTER_UP => actionUp(actionIndex, actionPointer, event)
      case ACTION_MOVE => actionMove(actionIndex, actionPointer, event)
    }
  }

  def actionDown(index: Int, pointer: Int, event: MotionEvent): Boolean = {
    val x = event.getX(index)
    val y = event.getY(index)
    game pushEvent PointerDown(pointer, (x, y))
    game startPushingEvent new PointerHold(pointer, (x, y))
    true
  }
  def actionUp(index: Int, pointer: Int, event: MotionEvent): Boolean = {
    val x = event.getX(index)
    val y = event.getY(index)
    game pushEvent PointerUp(pointer, (x, y))
    true
  }
  def actionMove(index: Int, pointer: Int, event: MotionEvent): Boolean = {
    val x = event.getX(index)
    val y = event.getY(index)
    game stopPushingEvent new PointerHold(pointer, (x, y))
    game pushEvent PointerMove(pointer, (x, y))
    true
  }

}