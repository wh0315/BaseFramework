package com.caifulife.baseframework.fargment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.caifulife.baseframework.R;
import com.caifulife.baseframework.view.NetCustomDialog;

/**
 * Created by 皓 on 2017/8/29.
 */

public abstract class BaseFragment extends Fragment  {

    View view;

    private NetCustomDialog dialog ;
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            ViewParent parent = view.getParent();
            ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.removeView(view);
        } else {
            view = setContentView();
        }
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        init();
    }
    /**
     * 设置初始页面
     *
     * @return
     */
    public abstract View setContentView();
    /**
     * 初始化操作
     */
    public abstract void init();
    /**
     * 初始化布局
     */
    public abstract void initView();

    public void showLoading() {
        if (null == dialog) {
            dialog = new NetCustomDialog(getActivity(), R.style.Theme_dialog);
        }
        dialog.show();
    }
    public void hideLoading() {
        if (null != dialog) {
            dialog.dismiss();
        }
    }




}
