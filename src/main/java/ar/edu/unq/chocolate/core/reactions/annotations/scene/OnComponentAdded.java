package ar.edu.unq.chocolate.core.reactions.annotations.scene;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ar.edu.unq.chocolate.core.reactions.annotations.GameTrigger;
import ar.edu.unq.chocolate.core.reactions.events.ComponentAdded;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@GameTrigger(ComponentAdded.class)
public @interface OnComponentAdded {
}
