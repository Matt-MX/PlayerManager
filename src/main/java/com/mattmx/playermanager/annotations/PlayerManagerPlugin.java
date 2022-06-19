package com.mattmx.playermanager.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface PlayerManagerPlugin {
    String id();
    String name();
    String[] authors();
    String url();
    String version();
    String description();
}
