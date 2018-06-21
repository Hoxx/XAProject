package com.yueshen.routerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yueshen.routerapp.upload.UploadMsgQueue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 10; i++) {
            UploadMsg msg = new UploadMsg();

            msg.setId(i);

            UploadMsgQueue.add(msg);
        }
    }


    public void Open(View view) {
        startService(new Intent(getApplicationContext(), UploadService.class));
    }
}
