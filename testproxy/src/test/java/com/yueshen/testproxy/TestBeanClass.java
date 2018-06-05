package com.yueshen.testproxy;

public class TestBeanClass {

    public String Address;
    private String name;
    private int num;
    private boolean flag;


    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private int add(int N) {
        return num + N;
    }

    public String getInfo() {
        return name + ":" + Address;
    }
}
