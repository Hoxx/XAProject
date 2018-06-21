package com.yueshen.routerapp;

import com.yueshen.routerapp.upload.Msg;

import java.util.ArrayList;
import java.util.List;

public class SingleClass {

    private volatile static SingleClass instance;


    public static SingleClass getInstance() {
        return instance;
    }

    private List<Msg> list = new ArrayList<>();


    public static <T extends Msg> void add(T t) {
        getInstance().addMsg(t);
    }


    public void addMsg(Msg msg) {
        list.add(msg);
    }




}
