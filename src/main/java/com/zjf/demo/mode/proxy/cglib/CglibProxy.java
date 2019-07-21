package com.zjf.demo.mode.proxy.cglib;import org.springframework.cglib.proxy.Enhancer;import org.springframework.cglib.proxy.MethodInterceptor;import org.springframework.cglib.proxy.MethodProxy;import java.lang.reflect.Method;/** * @author zhaojiafeng */public class CglibProxy implements MethodInterceptor {    Object getInstance(final Object object) {        Enhancer enhancer = new Enhancer();        enhancer.setSuperclass(object.getClass());        enhancer.setCallback(this);        return enhancer.create();    }    @Override    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {        System.out.println("start buy car, check money");        // 不要使用 invoke 方法        Object result = methodProxy.invokeSuper(o, objects);        System.out.println("pay money");        return result;    }}