package com.caifulife.baseframework.utils;

import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import com.caifulife.baseframework.appliaction.MyApplication;

/**
 * Created by 皓 on 2017/8/29.
 */

public class UIUtils  {

    public static String getString(int id) {
        return MyApplication.getInstance().getResources()
                .getString(id);
    }

    public static Context getContext() {
        return MyApplication.getInstance();
    }

    // Toast 提示语
    public static void showToast(final String msg) {
        runOnUIThread(new Runnable() {

            public void run() {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void runOnUIThread(Runnable runnable) {
        if (isMainUITid()) {
            runnable.run();
        } else {
            getHandler().post(runnable);
        }
    }
    private static boolean isMainUITid() {
        return android.os.Process.myTid() == getMainUiTid();
    }

    private static Handler getHandler() {
        return MyApplication.getHandler();
    }

    private static int getMainUiTid() {
        return MyApplication.getMainUITid();
    }
    static public int getScreenWidthPixels(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(dm);
        return dm.widthPixels;
    }

    static public int dipToPx(Context context, int dip) {
        return (int) (dip * getScreenDensity(context) + 0.5f);
    }

    static public float getScreenDensity(Context context) {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                    .getMetrics(dm);
            return dm.density;
        } catch (Exception e) {
            return DisplayMetrics.DENSITY_DEFAULT;
        }
    }
    /**
     * @return
     *
     *  // 获取当前屏幕  的密度
     */
    public static float getDensity() {
        DisplayMetrics dm = new DisplayMetrics();
        dm = getContext().getResources().getDisplayMetrics();
        return dm.density;
    }
    /**
     * 获取屏幕宽
     *$author wh
     *created at 2017/8/7 11:00
     */
    public static int getWindowWith(){
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width ;
    }
    /**
     * 获取屏幕高
     *$author wh
     *created at 2017/8/7 11:00
     */
    public static int getWindowHeith(){
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        int height = wm.getDefaultDisplay().getHeight();
        return height ;
    }


}
