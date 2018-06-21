package com.yueshen.routermodulea;

public class UploadService extends UploadMsgService<UploadEntity> {

    @Override
    public void upload(UploadEntity msg, UploadMsgListener listener) {

        //编写上传代码


//        uploadFile("","",new UploadListener{
//            void onUploadSuccess(){
//                 上传成功后，执行完相关逻辑，调用listener.onUploadSuccess();，通知父类上传成功，处理下一个文件
//                listener.onUploadSuccess();
//
//            }
//
//            void onUploadFailed(){
//                上传失败后，调用listener.onUploadFailed();，通知父类上传失败，，把失败的消息重新添加到消息队列，等待后处理，然后处理下一个文件
//                listener.onUploadFailed();
//
//
//            }
//        })


    }
}
