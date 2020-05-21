package com.github.qichengjian.tcc.common.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Tcc {

    String confirmMethod() default "";

    String cancelMethod() default "";
}
