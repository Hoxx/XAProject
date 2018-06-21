package com.yueshen.routermodulea;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class UploadMsgService<M extends Serializable> extends Service {

    public final static String UPLOAD_LIST = "upload_list";

    private List<M> uploadList;
    private int startId;

    private Handler handler;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (uploadList == null || uploadList.size() <= 0) {
                    stopSelf(startId);
                } else {
                    M m = uploadList.get(0);
                    uploadList.remove(0);
                    upload(m, new UploadListener(uploadList, m));
                }
                return false;
            }
        });
    }

    public static <M extends Serializable> void startService(Context context, Class<?> service, ArrayList list) {
        Intent intent = new Intent(context, service);
        intent.putExtra(UPLOAD_LIST, list);
        context.startService(intent);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.hasExtra(UPLOAD_LIST)) {
            uploadList = (List) intent.getSerializableExtra(UPLOAD_LIST);
        }
        this.startId = startId;
        handler.sendEmptyMessage(0);
        return super.onStartCommand(intent, flags, startId);
    }

    //上传结果的回调
    private class UploadListener implements UploadMsgListener {

        private List<M> list;
        private M msg;

        private UploadListener(List<M> list, M msg) {
            this.list = list;
            this.msg = msg;
        }

        @Override
        public void onUploadSuccess() {
            handler.sendEmptyMessage(0);
        }

        @Override
        public void onUploadFailed() {
            list.add(msg);
            handler.sendEmptyMessage(0);
        }
    }

    //定义抽象方法，需要自定义实现类，方便
    public abstract void upload(M msg, UploadMsgListener listener);


    public interface UploadMsgListener {

        void onUploadSuccess();

        void onUploadFailed();

    }

}
