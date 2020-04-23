package com.tao.demo.annontation;

public class DynamicDataSourceHolder {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static Object getDataSource(){
        return THREAD_LOCAL.get();
    }

    public static void putDataSource(String name){
        THREAD_LOCAL.set(name);
    }
    public static String getDataSource(String name){
        return THREAD_LOCAL.get();
    }
    public static void removeDataSource(){
        THREAD_LOCAL.remove();
    }
}
