package com.tao.demo.annontation;

import java.lang.annotation.*;

/**
 * @author IT
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface FruitProvider {

    public int id() default -1;

    public String name() default "";

    public String address() default "";
}
