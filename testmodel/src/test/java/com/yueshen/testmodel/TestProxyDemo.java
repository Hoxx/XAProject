package com.yueshen.testmodel;

import com.yueshen.testmodel.testproxy.DynamicProxyHandler;
import com.yueshen.testmodel.testproxy.IDoSomething;
import com.yueshen.testmodel.testproxy.IProxyClass;
import com.yueshen.testmodel.testproxy.ProxyClassImp;
import com.yueshen.testmodel.testproxy.SingProxy;

import org.junit.Test;

//测试类
public class TestProxyDemo {

    public static int sing(IDoSomething something, int sum) {
        return something.doSomething(sum);
    }

    @Test
    public void main() {
        System.out.println(TestProxyDemo.sing(new SingProxy(), 5));
    }

    @Test
    public void ProxyClass() {
        ProxyClassImp proxyClassImp = new ProxyClassImp();

        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler(proxyClassImp);

        IProxyClass proxyClass = (IProxyClass) dynamicProxyHandler.newProxyInstance(IProxyClass.class.getInterfaces());

        proxyClass.doSomething(100);

        proxyClass.addFunction("测试动态修改代码");

    }

}
