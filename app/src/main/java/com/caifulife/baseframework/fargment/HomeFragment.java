package com.caifulife.baseframework.fargment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import com.caifulife.baseframework.R;
import com.caifulife.baseframework.activity.LoginActivity;
/**
 * Created by 皓 on 2017/8/29.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    @Override
    public View setContentView() {

        if (view == null) {
            view = View.inflate(getActivity(), R.layout.homefragment, null);
        }
        return view;
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {
        view.findViewById(R.id.button).setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_slide_in, R.anim.anim_slide_out);
                break;
        }
    }
}