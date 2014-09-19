package ar.edu.unq.chocolate.core

import android.view.SurfaceView
import android.view.SurfaceHolder
import android.content.Context
import android.graphics.Canvas
import android.view.View.OnTouchListener
import ar.edu.unq.chocolate.core.reactions.EventAdapter

class GamePlayer(val game: Game) extends SurfaceView(game.asInstanceOf[Context]) with SurfaceHolder.Callback with Runnable {

  new EventAdapter(game) addListeners this

  @volatile
  var playerThread: Thread = null

  var _holder: SurfaceHolder = null

  getHolder().addCallback(this)

  override def surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int): Unit = {
    _holder = holder
  }
  override def surfaceCreated(holder: SurfaceHolder): Unit = {
    this.resume
    _holder = holder
  }
  override def surfaceDestroyed(holder: SurfaceHolder): Unit = {
    this.pause
    _holder = holder
  }

  // ****************************************************************
  // ** QUERIES
  // ****************************************************************

  def isPaused = playerThread != Thread.currentThread

  // ****************************************************************
  // ** OPERATIONS
  // ****************************************************************
  def resume() {
    playerThread = new Thread(GamePlayer.this, game.title)

    playerThread.start
  }

  def pause() {
    playerThread = null
    game.pause
  }

  override def run {
    playerThread = Thread.currentThread
    while (!isPaused)
      takeStep
  }

  def takeStep {
    var canvas: Canvas = null
    try {
      canvas = this._holder.lockCanvas(null)
      if (canvas != null) onDraw(canvas)
    } finally {
      if (canvas != null) _holder.unlockCanvasAndPost(canvas)
    }
  }

  override def onDraw(canvas: Canvas) {
    game takeStep canvas
  }

}