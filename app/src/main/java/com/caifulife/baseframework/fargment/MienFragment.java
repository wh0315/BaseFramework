package com.caifulife.baseframework.fargment;

import android.view.View;

import com.caifulife.baseframework.R;

/**
 * Created by 皓 on 2017/8/29.
 */

public class MienFragment extends BaseFragment {
    @Override
    public View setContentView() {
        if(view == null ){
            view = View.inflate(getActivity(), R.layout.mienfragment,null);
        }
        return view;
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {

    }
}
