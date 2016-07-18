package com.wzy.zhihudaily;

import android.app.Application;
import android.content.Context;

import com.wzy.zhihudaily.network.VolleyManager;


public class ZhihuApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        initData();
    }

    private void initData() {
        VolleyManager.getInstance().init(sContext);
    }

    public static Context getContext() {
        return sContext;
    }
}
