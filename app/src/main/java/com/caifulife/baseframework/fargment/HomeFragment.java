package com.caifulife.baseframework.fargment;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.caifulife.baseframework.R;
import com.caifulife.baseframework.activity.LoginActivity;
import com.caifulife.baseframework.appliaction.MyApplication;
import com.caifulife.baseframework.utils.UIUtils;
import com.caifulife.baseframework.view.gildeview.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

/**
 * Created by 皓 on 2017/8/29.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener,OnBannerListener {

    private Banner banner ;

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
        banner =  view.findViewById(R.id.banner);
        banner.setImages(MyApplication.images)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();
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

    @Override
    public void OnBannerClick(int position) {
        UIUtils.showToast("你点击了："+position);
    }
}