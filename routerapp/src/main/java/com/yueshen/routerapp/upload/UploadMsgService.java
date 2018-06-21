package com.yueshen.routerapp.upload;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public abstract class UploadMsgService<M extends Msg> extends Service implements UploadMsgQueue.onMsgListener<M> {

    private int startId;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UploadMsgQueue.setOnMsgListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId = startId;
        UploadMsgQueue.poll();
        return super.onStartCommand(intent, flags, startId);
    }

    //上传结果的回调
    private class UploadListener implements UploadMsgListener {

        private M msg;

        private UploadListener(M msg) {
            this.msg = msg;
        }

        @Override
        public void onUploadSuccess() {
            UploadMsgQueue.poll();
        }

        @Override
        public void onUploadFailed() {
            UploadMsgQueue.add(msg);
            UploadMsgQueue.poll();
        }
    }

    @Override
    public void onNext(M msg) {
        upload(msg, new UploadListener(msg));
    }

    @Override
    public void onComplete() {
        Log.e("TAG", "result:onComplete");
        stopSelf(startId);
    }

    public abstract void upload(M msg, UploadMsgListener listener);


    public interface UploadMsgListener {

        void onUploadSuccess();

        void onUploadFailed();

    }

}
