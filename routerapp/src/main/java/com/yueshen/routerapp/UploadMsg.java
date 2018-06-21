package com.yueshen.routerapp;

import com.yueshen.routerapp.upload.Msg;

public class UploadMsg implements Msg {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}