package com.caifulife.baseframework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.caifulife.baseframework.activity.BaseActivity;
import com.caifulife.baseframework.appliaction.MyApplication;
import com.caifulife.baseframework.fargment.HomeFragment;
import com.caifulife.baseframework.fargment.MienFragment;
import com.caifulife.baseframework.fargment.MineFragment;
import com.caifulife.baseframework.fargment.SubjectFragment;
import com.caifulife.baseframework.utils.UIUtils;
import com.caifulife.baseframework.view.TabView;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity implements TabView.OnTabChangeListener {

    private TabView mTable ;
    private Map<String , Fragment> fragments = null ;
    private FragmentTransaction transaction;
    private String provideTag = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTable = (TabView) findViewById(R.id.view_tab);
        mTable.setOnTabChangeListener(this);
        fragments = new HashMap<>();
        mTable.setCurrentTab(0);
    }
    @Override
    protected boolean enableSliding() {
        return false;
    }

    @Override
    public void onTabChange(final String tag) {
        transaction = getSupportFragmentManager().beginTransaction();
        if (addFragmetsToContainer(transaction, tag)) {
            fragments.get(provideTag).onPause();
            fragments.get(tag).onResume();
            transaction.hide(fragments.get(provideTag));
            transaction.show(fragments.get(tag));
        } else {
            if (provideTag != null) {
                fragments.get(provideTag).onPause();
                transaction.hide(fragments.get(provideTag));
            }
        }
        transaction.commit();
        provideTag = tag;
    }


    public boolean addFragmetsToContainer (FragmentTransaction transaction ,String tag)
    {
        if (!fragments.containsKey(tag)) {
            /*
            *第一个板块
            */
            if (StringUtils.equals(tag, UIUtils.getString(R.string.tag_1))) {
                HomeFragment homeFragment = new HomeFragment();
                transaction.add(R.id.layout_content, homeFragment,
                        UIUtils.getString(R.string.tag_1));
                fragments.put(UIUtils.getString(R.string.tag_1), homeFragment);
            }
            /*
            *第二个板块
            */
            if (StringUtils.equals(tag, UIUtils.getString(R.string.tag_2))) {
                SubjectFragment subjectFragment = new SubjectFragment();
                transaction.add(R.id.layout_content, subjectFragment,
                        UIUtils.getString(R.string.tag_2));
                fragments.put(UIUtils.getString(R.string.tag_2),
                        subjectFragment);
            }
            /*
            *第三个板块
            */
            if (StringUtils.equals(tag, UIUtils.getString(R.string.tag_3))) {
                MienFragment mienFragment = new MienFragment();
                transaction.add(R.id.layout_content, mienFragment,
                        UIUtils.getString(R.string.tag_3));
                fragments.put(UIUtils.getString(R.string.tag_3), mienFragment);
            }
            /*
            *第四个板块
            */
            if (StringUtils.equals(tag, UIUtils.getString(R.string.tag_4))) {
                MineFragment mineFragment = new MineFragment();
                transaction.add(R.id.layout_content, mineFragment,
                        UIUtils.getString(R.string.tag_4));
                fragments.put(UIUtils.getString(R.string.tag_4), mineFragment);
            }
            return false;
        }
        return true;
    }
    /** 再按一次退出程序 */
    private long exitTime = 0;
    @Override
    public void back() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            UIUtils.showToast("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            MyApplication.getInstance().exit();
        }
    }
}
