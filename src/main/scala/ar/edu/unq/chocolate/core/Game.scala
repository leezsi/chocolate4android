package ar.edu.unq.chocolate.core

import android.os.Bundle
import android.app.Activity
import android.view.WindowManager
import android.view.Window
import android.graphics.Canvas
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Point
import ar.edu.unq.example.R
import ar.edu.unq.chocolate.core.loaders.AndroidResourceLoader
import ar.edu.unq.chocolate.core.reactions.events.GameEvent
import ar.edu.unq.chocolate.core.reactions.ContinuableEvent
import ar.edu.unq.chocolate.core.reactions.events.SceneSetAsCurrent
import ar.edu.unq.chocolate.core.appearances.Sprite
import android.content.Context

trait Game extends Activity{


    override def onCreate(savedInstanceState: Bundle) {
      requestWindowFeature(Window.FEATURE_NO_TITLE);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      setContentView(new GamePlayer(this))
      this.gameScript()
      super.onCreate(savedInstanceState);
    }

  var _currentScene: GameScene = null
  def currentScene = _currentScene
  def currentScene_=(scene: GameScene) {
    if (currentScene != null) currentScene.game = null

    _currentScene = scene
    scene.game = this
    this.pushEvent(SceneSetAsCurrent())
  }

  val resourceLoader = AndroidResourceLoader(this)

  currentScene = new GameScene

  def displaySize = {
    val display = this.getWindowManager().getDefaultDisplay();
    val size = new Point();
    display.getSize(size);
    (size.x, size.y)
  }

  def gameScript()

  // ****************************************************************
  // ** QUERIES
  // ****************************************************************

  def displayWidth = displaySize._1

  def displayHeight = displaySize._2

  def title: String

  // ****************************************************************
  // ** OPERATIONS
  // ****************************************************************

  def pushEvent(event: GameEvent) = currentScene pushEvent event

  def startPushingEvent(event: ContinuableEvent) = currentScene startPushingEvent event

  def stopPushingEvent(event: ContinuableEvent) = currentScene stopPushingEvent event

  def takeStep(graphics: Canvas) = currentScene takeStep graphics

  def pause = currentScene.pause

}