package com.example.perk.guidetest0702;

import android.app.Application;

import com.huiwu.model.http.ConnectionUtil;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;

/**
 * Created by perk on 2016/7/2.
 */
public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        CrashHandler handler = CrashHandler.getInstance(getApplicationContext());
//        handler.init(getApplicationContext());


        initOkHttp();

    }

    private void initOkHttp() {
        ConnectionUtil.getInstance().debug(true);

        OkHttpUtils.init(this);
        OkHttpUtils.getInstance()//
                .debug("OkHttpUtils1")
                .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)
                .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)
                .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)
//                .setCookieStore(new MemoryCookieStore())
//                .addCommonParams(params)
                .setCookieStore(new PersistentCookieStore());
    }
}
