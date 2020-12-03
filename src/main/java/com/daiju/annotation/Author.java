package com.daiju.annotation;

import java.lang.annotation.*;

/**
 * @Author WDY
 * @Date 2020-11-29 13:48
 * @Description TODO
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Author {
    boolean value() default true;
}
