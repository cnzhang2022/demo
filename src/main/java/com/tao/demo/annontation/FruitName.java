package com.tao.demo.annontation;

import java.lang.annotation.*;

/**
 * @author IT
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface FruitName {
    String value() default "";
}
