package org.chobit.ash.core.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Target(METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Put {

    @AliasFor("path")
    String value() default "";

    @AliasFor("value")
    String path() default "";

    String[] headers() default {};
}
