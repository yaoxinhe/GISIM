package com.example.bawei.addfriend.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baweigame.xmpplibrary.XmppManager;
import com.bigkoo.alertview.AlertView;
import com.example.bawei.addfriend.R;
import com.example.bawei.addfriend.adapter.MyAddFriendAdapter;
import com.example.bawei.addfriend.bean.AddBean;
import com.example.bawei.addfriend.bean.MyBeana;
import com.example.bawei.addfriend.contract.Contract;
import com.example.bawei.addfriend.presenter.Presenter;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.bawei.basemodel.ui.BaseFragment;
import com.example.library.ZXing3;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends BaseFragment implements Contract.View {
    private MyAddFriendAdapter myAddFriendAdapter;
    private SearchView serchview;
    private LinearLayout add_phone;
    private LinearLayout add_facetoface;
    Presenter presenter;
    List<MyBeana.DataBean> list = new ArrayList<>();
    private RecyclerView addfriend_recycle;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView(Bundle savedInstanceState) {
        serchview = mRoot.findViewById(R.id.serchview);
        add_phone = mRoot.findViewById(R.id.add_phone);
        add_facetoface = mRoot.findViewById(R.id.add_facetoface);
        addfriend_recycle = mRoot.findViewById(R.id.addfriend_recycle);
        presenter = new Presenter(this);
        myAddFriendAdapter = new MyAddFriendAdapter(R.layout.addfrienditem, list);
        serchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("CheckResult")
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.addFriend(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        myAddFriendAdapter.setOnItemClickListener((adapter, view, position) -> {
            SharedPreferences yxh = getContext().getSharedPreferences("yxh", 0);
            String usercode = yxh.getString("usercode", "");
            if (usercode != "") {
                presenter.add(usercode, list.get(position).getUsercode(), list.get(position).getUsername()+"@"+XmppManager.getInstance().getXmppConfig().getDomainName(), list.get(position).getUsername() );
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doEvent() {
        add_phone.setOnClickListener(v -> startActivity(new Intent(getContext(), AddFriendActivity.class)));
        add_facetoface.setOnClickListener(v -> new AlertView("扫码", null, "取消", null,
                new String[]{"我的二维码", "扫一扫"},
                getContext(), AlertView.Style.ActionSheet, (o, position) -> {
            Log.e("ite", position + "");
            if (position == 1) {
                ZXing3.openCamera(getActivity(), 101);

            } else if (position == 0) {
                Intent intent = new Intent(getContext(), MyImageActivity.class);

                startActivity(intent);
            }
        }).show());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showHideIng() {

    }

    @Override
    public <T> void Success(T bean, int flag) {
        if (flag == 101) {
            list.clear();
            MyBeana addFridentBeanBaseBeanEntity = (MyBeana) bean;
            list.addAll(addFridentBeanBaseBeanEntity.getData());
            addfriend_recycle.setLayoutManager(new LinearLayoutManager(getContext()));
            addfriend_recycle.setAdapter(myAddFriendAdapter);
            myAddFriendAdapter.notifyDataSetChanged();
        } else if(flag==102){
            Toast.makeText(getContext(), "添加成功", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void Failed() {

    }

    @Override
    public LifecycleOwner getOwner() {
        return this;
    }
}
