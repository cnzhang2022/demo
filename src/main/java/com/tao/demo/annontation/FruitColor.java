package com.tao.demo.annontation;

import java.lang.annotation.*;

/**
 * @author IT
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface FruitColor {

    public enum Color{BLUE, RED, GREEN};

    Color fruitColor() default Color.GREEN;
}
