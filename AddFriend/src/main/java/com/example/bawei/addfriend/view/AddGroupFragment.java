package com.example.bawei.addfriend.view;


import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.common.Config;
import com.bawei6.common.LogUtils;
import com.example.bawei.addfriend.R;
import com.example.bawei.addfriend.contract.Contract;
import com.example.bawei.addfriend.presenter.Presenter;
import com.example.bawei.basemodel.ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class AddGroupFragment extends BaseFragment implements Contract.View {

    private RecyclerView group_grouprecycleview;
    private Presenter presenter;

    public AddGroupFragment() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_group;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        group_grouprecycleview=mRoot.findViewById(R.id.group_grouprecycleview);
        presenter=new Presenter(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doEvent() {

    }

    @Override
    public <T> void Success(T bean, int flag) {

    }

    @Override
    public void Failed() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showHideIng() {

    }

    @Override
    public LifecycleOwner getOwner() {
        return this;
    }
}
