package com.hxx.xaproject.mm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public abstract class ListBaseFragment extends BasicFragment {


    RecyclerView recyclerView;

    private View searchView;//顶部搜索
    private View upView;//上一页
    private View nextView;//下一页

    //这个方法，继承自BaseFragment，设置布局的资源ID
    @Override
    public int inflaterLayoutId() {
        return 0;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.Adapter adapter=createAdapter();
        recyclerView.setAdapter(adapter);
    }

    //创建适配器
    public RecyclerView.Adapter createAdapter(){
        return new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        };
    }

    public void ViewControl(){
        //TODO 控制顶部与底部View的显示与隐藏
    }


//    RecyclerView.Adapter adapter = getAdapter();
//        if (adapter == null) {
//        Log.e("TAG", "Adapter Not is Null");
//        return;
//    }
//        recyclerView.setAdapter(adapter);
    public abstract RecyclerView.Adapter getAdapter();





    class AAAdapter extends RecyclerView.Adapter{

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }


}
