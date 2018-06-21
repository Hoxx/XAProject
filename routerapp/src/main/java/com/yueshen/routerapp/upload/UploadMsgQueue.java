package com.yueshen.routerapp.upload;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

public class UploadMsgQueue implements Handler.Callback {

    private static volatile UploadMsgQueue instance;
    //消息处理
    private Handler msgHandler;
    //消息监听
    private onMsgListener<Msg> onMsgListener;
    //消息容器
    private List<Msg> msgList;

    private UploadMsgQueue() {
//        msgList = new ArrayList<>();
        msgHandler = new Handler(Looper.myLooper(), this);
    }

    private static UploadMsgQueue getInstance() {
        if (instance == null) {
            synchronized (UploadMsgQueue.class) {
                if (instance == null) {
                    instance = new UploadMsgQueue();
                }
            }
        }
        return instance;
    }

    //添加消息
    public static void add(List<Msg> list) {
        getInstance().addList(list);
    }

    //添加消息
    public static void add(Msg m) {
        getInstance().addMsg(m);
    }

    //开始
    public static void poll() {
        getInstance().getMsg();
    }

    //设置监听
    public static void setOnMsgListener(onMsgListener listener) {
        getInstance().setListener(listener);
    }

    private void addList(List<Msg> list) {
        if (list == null || list.size() <= 0) return;
        msgList = list;
    }

    //添加消息
    private void addMsg(Msg msg) {
        if (msgList == null)
            msgList = new ArrayList<>();
        if (!msgList.contains(msg)) {
            msgList.add(msg);
        }
    }

    //轮询消息
    private void getMsg() {
        if (getMessageLength() > 0)
            msgHandler.sendEmptyMessage(0);
        else if (onMsgListener != null)
            onMsgListener.onComplete();
    }

    //消息监听
    private void setListener(onMsgListener listener) {
        onMsgListener = listener;
    }

    //取出下一个消息
    private Msg next() {
        Msg msg = null;
        if (getMessageLength() > 0) {
            msg = msgList.get(0);
            msgList.remove(0);
        }
        return msg;
    }

    //获取消息队列的长度
    private int getMessageLength() {
        return msgList == null ? 0 : msgList.size();
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (onMsgListener != null) {
            onMsgListener.onNext(next());
        }
        return false;
    }

    public interface onMsgListener<M> {
        void onNext(M msg);

        void onComplete();
    }
}
