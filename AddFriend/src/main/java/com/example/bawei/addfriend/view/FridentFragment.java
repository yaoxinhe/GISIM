package com.example.bawei.addfriend.view;


import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.common.PhoneDto;
import com.bawei6.common.PhoneUtil;
import com.example.bawei.addfriend.R;
import com.example.bawei.addfriend.adapter.GoodsAdapter;
import com.example.bawei.addfriend.adapter.MygetFriendAdapter;
import com.example.bawei.addfriend.bean.MyFriendBean;
import com.example.bawei.addfriend.contract.Contract;
import com.example.bawei.addfriend.presenter.Presenter;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.bawei.basemodel.ui.BaseFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FridentFragment extends BaseFragment implements Contract.View {
    private List<PhoneDto> phoneDtos;
    private RecyclerView addfrgment_recycle;
    private Presenter presenter;
    private MygetFriendAdapter mygetFriendAdapter;
    int i=0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_frident;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView(Bundle savedInstanceState) {
        addfrgment_recycle = (RecyclerView) mRoot.findViewById(R.id.addfragment_recycle);
        SharedPreferences yxh = getContext().getSharedPreferences("yxh", 0);
        String usercode = yxh.getString("usercode", "");
        if(usercode!=""){
            presenter=new Presenter(this);
            presenter.friend(usercode);
        }

//        check();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doEvent() {

    }
    /**
     * 检查权限
     */
    private void check() {
        //判断是否有权限
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 201);
        } else {
            initViews();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 201) {
            LogUtils.e("asdsadsadas");
            initViews();
        } else {
            return;
        }
    }


    private void initViews() {
        PhoneUtil phoneUtil = new PhoneUtil(getContext());
        phoneDtos = phoneUtil.getPhone();
        LogUtils.e(phoneDtos.size() + "");
        addfrgment_recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        addfrgment_recycle.setAdapter(new GoodsAdapter(phoneDtos));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showHideIng() {

    }

    @Override
    public void onStart() {
        super.onStart();
        i++;
        if(i<1){
            mygetFriendAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public <T> void Success(T bean, int flag) {
       MyFriendBean myFriendBean =(MyFriendBean) bean;
        List<MyFriendBean.DataBean> data = myFriendBean.getData();
         mygetFriendAdapter = new MygetFriendAdapter(R.layout.addfrienditem, data);
        addfrgment_recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        addfrgment_recycle.setAdapter(mygetFriendAdapter);
        mygetFriendAdapter.notifyDataSetChanged();
    }

    @Override
    public void Failed() {

    }

    @Override
    public LifecycleOwner getOwner() {
        return this;
    }
}
