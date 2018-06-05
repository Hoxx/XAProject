package com.yueshen.testproxy;

//被代理类
public class ProxyClassImp implements IProxyClass {
    @Override
    public int doSomething(int i) {
        System.out.println("被代理类-方法执行中.....");
        return i;
    }

    @Override
    public String addFunction(String msg) {
        System.out.println("被代理类-添加方法执行中.....：" + msg);
        return msg;
    }
}
