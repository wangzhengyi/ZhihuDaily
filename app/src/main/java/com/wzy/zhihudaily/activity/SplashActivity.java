package com.wzy.zhihudaily.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.wzy.zhihudaily.R;
import com.wzy.zhihudaily.ZhihuApplication;
import com.wzy.zhihudaily.model.StartImageBean;
import com.wzy.zhihudaily.network.HttpUtil;
import com.wzy.zhihudaily.network.InputStreamVolleyRequest;
import com.wzy.zhihudaily.network.VolleyManager;
import com.wzy.zhihudaily.util.Constant;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SplashActivity extends AppCompatActivity {
    public static final int SCALE_ANIMATION_DURATION = 3000;

    private ImageView mSplashImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initData();
        initView();
        loadData();
    }

    private void initData() {

    }

    private void initView() {
        mSplashImg = (ImageView) findViewById(R.id.id_splash_iv);
    }

    private void loadData() {
        File dir = getFilesDir();
        final File splashFile = new File(dir, Constant.SPLASH_FILENAME);
        if (splashFile.exists()) {
            mSplashImg.setImageBitmap(BitmapFactory.decodeFile(splashFile.getAbsolutePath()));
        } else {
            mSplashImg.setImageResource(R.drawable.splash);
        }

        final ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(SCALE_ANIMATION_DURATION);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (HttpUtil.isNetworkConnected(ZhihuApplication.getContext())) {
                    final RequestQueue requestQueue = VolleyManager.getInstance().getRequestQueue();
                    String downloadUrl = Constant.BASEURL + Constant.SPLASH_IMG;
                    StringRequest stringRequest = new StringRequest(downloadUrl,
                            new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            StartImageBean sibean =
                                    new Gson().fromJson(response, StartImageBean.class);
                            if (sibean != null && !TextUtils.isEmpty(sibean.getImg())) {
                                InputStreamVolleyRequest inputStreamVolleyRequest =
                                        createInputStreamRequest(sibean.getImg());
                                requestQueue.add(inputStreamVolleyRequest);
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            startMainActivity();
                        }
                    });
                    requestQueue.add(stringRequest);
                } else {
                    // TODO: 无网络提醒
                    startMainActivity();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mSplashImg.startAnimation(scaleAnim);
    }

    @NonNull
    private InputStreamVolleyRequest createInputStreamRequest(String url) {
        return new InputStreamVolleyRequest(
                Request.Method.GET, url, new Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] response) {
                saveSplashImg(response);
                startMainActivity();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                startMainActivity();
            }
        }, null);
    }

    private void saveSplashImg(byte[] response) {
        File dir = getFilesDir();
        File splashFile = new File(dir, Constant.SPLASH_FILENAME);
        if (splashFile.exists()) {
            splashFile.delete();
        }

        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(splashFile));
            bos.write(response);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
