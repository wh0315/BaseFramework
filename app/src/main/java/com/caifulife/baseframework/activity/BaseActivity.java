package com.caifulife.baseframework.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.caifulife.baseframework.R;
import com.caifulife.baseframework.appliaction.MyApplication;
import com.caifulife.baseframework.view.NetCustomDialog;
import com.caifulife.baseframework.view.SlidingLayout;

/**
 * Created by 皓 on 2017/8/29.
 */

public abstract class BaseActivity extends FragmentActivity {

    private NetCustomDialog dialog ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        //侧滑返回
        if (enableSliding()) {
            SlidingLayout rootView = new SlidingLayout(this);
            rootView.bindActivity(this);
        }
    }

    protected boolean enableSliding() {
        return true;
    }


    public void addFragment(String Tag, Fragment arg1) {
        if (getSupportFragmentManager().findFragmentByTag(Tag) == null) {
            final FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction();
            ft.add(android.R.id.content, arg1, Tag);
            ft.commit();
        }
    }

    public void showLoading() {
        if (null == dialog) {
            dialog = new NetCustomDialog(BaseActivity.this, R.style.Theme_dialog);
        }
        dialog.show();
    }
    public void hideLoading() {
        if (null != dialog) {
            dialog.dismiss();
        }
    }


    public void back() {
        finish();
    }


    @Override
    public void onBackPressed() {
        back();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
