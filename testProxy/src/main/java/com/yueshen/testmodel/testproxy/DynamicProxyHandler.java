package com.yueshen.testmodel.testproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyHandler implements InvocationHandler {

    private Object proxied;

    /**
     * @param proxied 被代理对象
     */
    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    //返回代理对象
    public Object newProxyInstance() {
        return Proxy.newProxyInstance(proxied.getClass().getClassLoader(), proxied.getClass().getInterfaces(), this);
    }

    //返回代理对象
    public Object newProxyInstance(Class<?>[] interfaces) {
        return Proxy.newProxyInstance(proxied.getClass().getClassLoader(), interfaces, this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在目标对象的方法执行之前简单的打印一下
        System.out.println("------------------before------------------");
        // 执行目标对象的方法
        Object result = method.invoke(proxied, args);
        // 在目标对象的方法执行之后简单的打印一下
        System.out.println("-------------------after------------------");
        return result;
    }


}
