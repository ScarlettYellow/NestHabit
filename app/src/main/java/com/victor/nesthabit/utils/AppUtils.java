package com.victor.nesthabit.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by victor on 7/2/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science
 *  #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG              #
 * #                                                   #
 */


public class AppUtils {
    private static Context mContext;
    private static Thread mUiThread;

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void init(Context context) { //在Application中初始化
        mContext = context;
        mUiThread = Thread.currentThread();
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static AssetManager getAssets() {
        return mContext.getAssets();
    }

    public static Resources getResource() {
        return mContext.getResources();
    }

    public static boolean isUIThread() {
        return Thread.currentThread() == mUiThread;
    }

    public static void runOnUI(Runnable r) {
        sHandler.post(r);
    }

    public static void runOnUIDelayed(Runnable r, long delayMills) {
        sHandler.postDelayed(r, delayMills);
    }

    public static void removeRunnable(Runnable r) {
        if (r == null) {
            sHandler.removeCallbacksAndMessages(null);
        } else {
            sHandler.removeCallbacks(r);
        }
    }
}
