package ar.edu.unq.chocolate.core.reactions

import scala.collection.mutable.ArrayBuffer
import ar.edu.unq.chocolate.core.reactions.events.GameEvent
import ar.edu.unq.chocolate.core.reactions.events.Update
import scala.collection.mutable.HashMap

class EventQueue {
  var events = new ArrayBuffer[GameEvent]
  var continuations = new HashMap[EventSignature, EventContinuation]

  reset

  // ****************************************************************
  // ** OPERATIONS
  // ****************************************************************

  def pushEvent(event: GameEvent) = synchronized { events += event }

  def startPushingEvent(event: ContinuableEvent) = synchronized {
    continuations put (event.mainSignature, new EventContinuation(event))
  }

  def stopPushingEvent(event: ContinuableEvent) = synchronized {
    continuations remove event.mainSignature
  }

  def takePendingEvents = synchronized {
    val answer = events ++ takeContinuationEvents
    resetEvents
    answer
  }

  def reset {
    resetContinuations
    resetEvents

    startPushingEvent(new Update)
  }

  def pause = synchronized { continuations.values foreach { _.reset } }

  protected def takeContinuationEvents = synchronized {
    continuations.values map (_.createEvent)
  }

  protected def resetEvents = synchronized { events.clear }

  protected def resetContinuations = synchronized { continuations.clear }

}