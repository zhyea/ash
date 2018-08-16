package org.chobit.ash.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * robin
 */

@Target(METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Delete {


    @AliasFor("path")
    String value() default "";

    @AliasFor("value")
    String path() default "";

    String[] headers() default {};

}
