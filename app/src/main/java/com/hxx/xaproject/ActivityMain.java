package com.hxx.xaproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yueshen.xannotation.FindId;
import com.yueshen.xannotationprocessor.XAnnotationProcessor;

public class ActivityMain extends AppCompatActivity {

    FragmentManager fragmentManager;


    @FindId(R.id.tv_main_title)
    TextView main_tv;


    TabBean<BFragment> bFragmentTabBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XAnnotationProcessor.bind(this);

        bFragmentTabBean = new TabBean<>(BFragment.class);

        fragmentManager = getSupportFragmentManager();

        main_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityMain.this, "点击切换", Toast.LENGTH_SHORT).show();
                changeFragment(bFragmentTabBean.getFragment());
            }
        });

        //第一步，动态添加第一个Fragment
        fragmentManager.beginTransaction().add(R.id.lin_main, new AFragment(), "AFragment").commit();


    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Log.e("TAG", "点击返回键");
//        fragmentManager.popBackStack();
//    }

    public void changeFragment(Fragment fragment) {
        if (fragment == null) {
            Log.e("TAG", "fragment is null");
            return;
        }
        String tag = fragment.getClass().getSimpleName();
        fragmentManager
                .beginTransaction()
                .replace(R.id.lin_main, new BFragment(), tag)
                .addToBackStack(tag)
                .commit();
    }
}
