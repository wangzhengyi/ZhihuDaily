package com.wzy.zhihudaily.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Volley请求单例类,封装HTTP请求.
 */
public class VolleyManager {
    private static volatile VolleyManager sInstance;

    private RequestQueue mRequestQueue;

    private VolleyManager() {

    }

    public static VolleyManager getInstance() {
        if (sInstance == null) {
            synchronized (VolleyManager.class) {
                if (sInstance == null) {
                    sInstance = new VolleyManager();
                }
            }
        }

        return sInstance;
    }

    public void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
