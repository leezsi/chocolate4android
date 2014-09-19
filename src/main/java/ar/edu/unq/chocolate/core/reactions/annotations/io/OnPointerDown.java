package ar.edu.unq.chocolate.core.reactions.annotations.io;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ar.edu.unq.chocolate.core.reactions.annotations.GameTrigger;
import ar.edu.unq.chocolate.core.reactions.events.PointerDown;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@GameTrigger(PointerDown.class)
public @interface OnPointerDown {
}
