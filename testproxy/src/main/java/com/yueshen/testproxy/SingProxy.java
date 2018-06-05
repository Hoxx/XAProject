package com.yueshen.testproxy;

//代理类的实现
public class SingProxy implements IDoSomething {

    private IDoSomething sing = new Sing();


    @Override
    public int doSomething(int sum) {
        System.out.println("BeFor singing ");
        int result = sing.doSomething(sum);
        System.out.println("After singing");
        return result;
    }
}
