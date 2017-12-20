package com.caifulife.baseframework.fargment;

import android.content.Intent;
import android.view.View;

import com.caifulife.baseframework.R;
import com.caifulife.baseframework.activity.LoginActivity;

/**
 * Created by 皓 on 2017/8/29.
 */

public class SubjectFragment extends BaseFragment implements View.OnClickListener {
    @Override
    public View setContentView() {
        if(view == null ){
            view = View.inflate(getActivity(), R.layout.subjectfragment,null);
        }
        return view;
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {
        view.findViewById(R.id.button2).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_slide_in,R.anim.anim_slide_out);
                break;
        }
    }
}
