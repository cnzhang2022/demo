package com.tao.demo.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogAnnotation {

    String operateModelNm() default "";
    String operateFuncNm() default "";
    String operateDescribe() default "";
}
