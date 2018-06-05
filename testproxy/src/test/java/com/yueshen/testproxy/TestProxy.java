package com.yueshen.testproxy;

import org.junit.Test;

//测试类
public class TestProxy {

    @Test
    public void main() {
        System.out.println(new SingProxy().doSomething(5));
    }

    @Test
    public void ProxyClass() {
        ProxyClassImp proxyClassImp = new ProxyClassImp();

        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler(proxyClassImp);

        IProxyClass proxyClass = (IProxyClass) dynamicProxyHandler.newProxyInstance(proxyClassImp.getClass().getInterfaces());

        proxyClass.doSomething(100);

        proxyClass.addFunction("测试动态修改代码");

    }

}
