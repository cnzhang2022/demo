package com.tao.demo.annontation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Component
@Aspect
public class DataSourceAspect {

    @Pointcut("execution( * com.tao.demo.*.dao.*(..))")
    public void dataSourcePointCut(){
        System.out.println("----DataSourceAspect dataSourcePointCut-----");
    }

    @Before("dataSourcePointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("----DataSourceAspect before-----");
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?>[] clazz = target.getClass().getInterfaces();
        Class<?>[] paramTypes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = clazz[0].getMethod(method, paramTypes);
            if(m!=null && m.isAnnotationPresent(DataSource.class)){
                DataSource ds = m.getAnnotation(DataSource.class);
                String dataSourceName = ds.value();
                DynamicDataSourceHolder.putDataSource(dataSourceName);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    public void after(JoinPoint joinPoint){
        System.out.println("3333");
        DynamicDataSourceHolder.removeDataSource();
    }
}
