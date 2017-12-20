package com.caifulife.baseframework.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.caifulife.baseframework.R;
import com.caifulife.baseframework.view.gildeview.GlideCircleTransform;
import com.caifulife.baseframework.view.gildeview.GlideRoundTransform;

public class LoginActivity extends BaseActivity {

    private ImageView imageView,imageView1 ,imageView2;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Explode().setDuration(2000));
        getWindow().setExitTransition(new Explode().setDuration(2000));
        setContentView(R.layout.activity_login);
        imageView = (ImageView) findViewById(R.id.imageView) ;
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //出现加载框
               // LoginActivity.this.showLoading();
                //隐藏加载框
               // LoginActivity.this.hideLoading();
            }
        });




        RequestManager glideRequest = Glide.with(this);
        glideRequest.load("https://www.baidu.com/img/bdlogo.png").into(imageView);
        //圆角
        glideRequest.load("https://www.baidu.com/img/bdlogo.png").transform(new GlideRoundTransform(this)).into(imageView1);
        //圆形
        glideRequest.load("https://www.baidu.com/img/bdlogo.png").transform(new GlideCircleTransform(this)).into(imageView2);



    }

    /**
     * 此方法决定当前activity页面是否具有侧滑返回的
     * true 具备
     * false 不具备
     * 如果不重写择默认是具备侧滑返回
     *$author wh
     *created at 2017/12/20 14:25
     */
    @Override
    protected boolean enableSliding() {
        return false;
    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }

}
