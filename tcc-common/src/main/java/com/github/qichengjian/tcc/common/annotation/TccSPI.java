package com.github.qichengjian.tcc.common.annotation;

import java.lang.annotation.*;

/**
 * tcc spi注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TccSPI {

    String value() default "";
}
