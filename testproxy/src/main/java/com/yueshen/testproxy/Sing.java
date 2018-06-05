package com.yueshen.testproxy;

//被代理类的实现
public class Sing implements IDoSomething {

    @Override
    public int doSomething(int sum) {
        System.out.println("Sing a song");
        return sum;
    }

}
