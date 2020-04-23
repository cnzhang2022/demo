package com.tao.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Aspect
@Component
public class MyInterceptor {

    @Pointcut("execution(* com.tao.demo.aop.*.*(..))")
    private void anyMethod(){

    }
    // Around before method  Around  After AfterReturning
    @Before("anyMethod() && args(name)")
    public void doAccessCheck(String name){
        System.out.println(name);
        System.out.println("前置通知");
    }

    @AfterReturning("anyMethod()")
    public void doAfter(){
        System.out.println("后置通知");
    }

    @After("anyMethod()")
    public void after(JoinPoint point){
        System.out.println("最终通知");
    }

    @AfterThrowing("anyMethod()")
    public void doAfterThrow(JoinPoint point){
        System.out.println("例外通知");
    }

    @Around("anyMethod()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        Class[] parameterTypes = new Class[pjp.getArgs().length];
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            if(args[i]!=null){
                parameterTypes[i] = args[i].getClass();
            }else {
                parameterTypes[i] = null;
            }
        }
        // 获取代理方法对象
        String methodName = pjp.getSignature().getName();
        Method method = pjp.getSignature().getDeclaringType().getMethod(methodName, parameterTypes);
        if(method.isAnnotationPresent(LogAnnotation.class)){
           System.out.println("存在1");
        }
        // 获取实际方法对象,可以获取方法注解等
        Method realMethod = pjp.getTarget().getClass().getDeclaredMethod(signature.getName(), targetMethod.getParameterTypes());
        if (realMethod.isAnnotationPresent(LogAnnotation.class)) {
            realMethod.getAnnotation(LogAnnotation.class).operateDescribe();
            System.out.println("存在2");
        }
        System.out.println("进入环绕通知");
        Object object = pjp.proceed();
        System.out.println("退出方法"+object);
        return object;
    }
}
