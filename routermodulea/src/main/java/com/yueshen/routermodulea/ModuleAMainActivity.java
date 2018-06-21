package com.yueshen.routermodulea;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yueshen.routerapp.R;

import java.util.ArrayList;

public class ModuleAMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList list = new ArrayList();

        for (int i = 0; i < 10; i++) {
            UploadEntity entity = new UploadEntity();
            entity.setName("" + i);
            list.add(entity);
        }


        UploadService.startService(this, UploadService.class, list);

    }
}
