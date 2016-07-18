package com.wzy.zhihudaily.util;


import android.util.Log;

public class LogUtil {
    public static final int LEVEL = -1;
    public static final int VERBOSE = 0;
    public static final int DEDBUG = 1;
    public static final int INFO = 2;
    public static final int WARNING = 3;
    public static final int ERROR = 4;
    public static final int NOTHING = 5;

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE)
            Log.v(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEDBUG)
            Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO)
            Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARNING)
            Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR)
            Log.e(tag, msg);
    }
}
