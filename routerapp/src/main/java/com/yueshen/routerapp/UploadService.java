package com.yueshen.routerapp;

import android.util.Log;

import com.yueshen.routerapp.upload.UploadMsgService;

public class UploadService extends UploadMsgService<UploadMsg> {

    @Override
    public void upload(UploadMsg msg, UploadMsgListener listener) {
        Log.e("TAG", "result:" + msg.getId());
        listener.onUploadSuccess();
    }
}
