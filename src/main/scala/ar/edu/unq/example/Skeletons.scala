package ar.edu.unq.example

import ar.edu.unq.chocolate.core.Game
import ar.edu.unq.chocolate.core.utils.AppearanceFont
import ar.edu.unq.chocolate.core.utils.AppearanceColor._
import ar.edu.unq.chocolate.core.dimensions.Vector.touple_to_vector
import ar.edu.unq.chocolate.core.appearances.Animation
import ar.edu.unq.chocolate.core.GameScene
import ar.edu.unq.chocolate.core.components.Visible
import ar.edu.unq.chocolate.core.appearances.Label
import ar.edu.unq.chocolate.utils.commoncomponents.ui.Button
import ar.edu.unq.example.skeletons.components.Firebolt
import ar.edu.unq.example.skeletons.components.Wizard
import ar.edu.unq.chocolate.core.components.Collisionable
import ar.edu.unq.chocolate.core.collisions.RectangularBoundingBox
import ar.edu.unq.example.skeletons.components.Obstacle
import ar.edu.unq.example.skeletons.components.Spawner
import ar.edu.unq.example.skeletons.components.Skeleton
import ar.edu.unq.chocolate.core.loaders.AndroidResourceLoader
import android.app.Activity
import ar.edu.unq.chocolate.core.appearances.DrawableAnimation

class Skeletons extends Game {

  def title = "Skeletons"

  // ****************************************************************
  // ** CONSTANTS
  // ****************************************************************

  val menuButtonsFont = AppearanceFont.SANS_SERIF.bold
  //    new Font(Font.SANS_SERIF, Font.BOLD, 32)
  val aboutTextFont = AppearanceFont.SANS_SERIF.italic
  //    new Font(Font.SANS_SERIF, Font.ITALIC, 24)

  // ****************************************************************
  // ** RESOURCE LOADING
  // ****************************************************************

  def gameScript = {

    val backgroundImage = resourceLoader.loadSprite(R.drawable.floor)
      .crop(0, 75, 300, 225)
      .scaleBy(0.5)
    backgroundImage.repeatToCover(displaySize * 3)

    val wizardImage = resourceLoader.loadSprite(R.drawable.wizard)
      .crop(136, 4, 23, 38)
      .scaleBy(2)

    val brickPileTopImage = resourceLoader.loadSprite(R.drawable.bricks)
      .crop(399, 116)
      .scaleBy(0.33)

    val brickPileBaseImage = resourceLoader.loadSprite(R.drawable.bricks)
      .crop(0, 100, 399, 153)
      .scaleBy(0.33)

    val fireboltAnimation = {
        def source = resourceLoader.loadSprite(R.drawable.fire_ball).clone
      new DrawableAnimation(0.06,
        source.crop(0, 96, 32, 32),
        source.crop(32, 96, 32, 32),
        source.crop(64, 96, 32, 32)).scaleBy(1.3)
    }

    val explosionAnimation = {
        def source = resourceLoader.loadSprite(R.drawable.explosion).clone
      new DrawableAnimation(0.6 / 20,
        source.crop(0, 0, 320, 240),
        source.crop(320, 0, 320, 240),
        source.crop(640, 0, 320, 240),
        source.crop(960, 0, 320, 240),
        source.crop(0, 240, 320, 240),
        source.crop(320, 240, 320, 240),
        source.crop(640, 240, 320, 240),
        source.crop(960, 240, 320, 240),
        source.crop(0, 480, 320, 240),
        source.crop(320, 480, 320, 240),
        source.crop(640, 480, 320, 240),
        source.crop(960, 480, 320, 240),
        source.crop(0, 720, 320, 240),
        source.crop(320, 720, 320, 240),
        source.crop(640, 720, 320, 240),
        source.crop(960, 720, 320, 240),
        source.crop(0, 960, 320, 240),
        source.crop(320, 960, 320, 240),
        source.crop(640, 960, 320, 240),
        source.crop(960, 960, 320, 240)).scaleBy(0.45)
    }

    val skeletonAnimation = {
        def source = resourceLoader.loadSprite(R.drawable.skeleton).clone
      new DrawableAnimation(0.3,
        source.crop(0, 0, 32, 32),
        source.crop(32, 0, 32, 32),
        source.crop(64, 0, 32, 32)).scaleBy(2)
    }

    val mainScreenBackground = resourceLoader.loadSprite(R.drawable.main_screen).scaleTo(displaySize)

    // ****************************************************************
    // ** SCENE SET-UP
    // ****************************************************************

    val titleScreen = new GameScene
    val aboutScreen = new GameScene
    val level01 = new GameScene

    currentScene = titleScreen

    aboutScreen.addComponents(
      new Visible { val appearance = mainScreenBackground },
      new Visible {
        val appearance = new Label(aboutTextFont)(WHITE)(24)("This game is made with the Chocolate game engine.")
        translation = (50, 200)
      },
      //    new UICursor(fireboltAnimation.clone, new CircularBoundingBox(5)),
      new Button(new Label(menuButtonsFont)(WHITE)(32)("BACK"))(650, 300)(_ ⇒ currentScene = titleScreen))

    titleScreen.addComponents(
      new Visible { val appearance = mainScreenBackground },
      //    new UICursor(fireboltAnimation.clone, new CircularBoundingBox(5)),
      new Button(new Label(menuButtonsFont)(WHITE)(32)("PLAY"))(650, 300)(_ ⇒ currentScene = level01),
      new Button(new Label(menuButtonsFont)(WHITE)(32)("ABOUT"))(650, 400)(_ ⇒
        currentScene = aboutScreen))

    val wizzard = new Wizard(wizardImage)(_ ⇒ new Firebolt(fireboltAnimation.clone, explosionAnimation.clone))
    wizzard.align(_.center, _.middle)(displaySize / 2)

    val obstacleTop = new Visible { val appearance = brickPileTopImage; translation = (displayWidth - 300, 175); z = 20 }

    level01.addComponents(
      new Visible { val appearance = backgroundImage },
      new Collisionable {
        val boundingBox = new RectangularBoundingBox(displayWidth, 10)
        translation = (0, 0)
      },
      new Collisionable {
        val boundingBox = new RectangularBoundingBox(10, displayHeight)
        translation = (displayWidth - 10, 0)
      },
      new Collisionable {
        val boundingBox = new RectangularBoundingBox(displayWidth, 10)
        translation = (0, displayHeight - 10)
      },
      new Collisionable {
        val boundingBox = new RectangularBoundingBox(10, displayHeight)
        translation = (0, 0)
      },

      wizzard,

      obstacleTop,
      new Obstacle(brickPileBaseImage, new RectangularBoundingBox(brickPileBaseImage.width - 6, 38))(obstacleTop, 16 * 0.33 + 1),

      new Spawner(2)(_ ⇒ new Skeleton(skeletonAnimation.clone, wizzard)))
  }
}