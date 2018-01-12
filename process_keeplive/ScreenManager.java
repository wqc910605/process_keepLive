package com.test.zysnail.www.process_keeplive;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.test.zysnail.www.tinker.app.MyApplication;

import java.lang.ref.WeakReference;

/**
 * Created by zy01060 on 2018/1/9.
 */

public class ScreenManager {

    private Context mContext;

    private WeakReference<Activity> mActivityWref;

    public static ScreenManager gDefualt;

    public static ScreenManager getInstance(Context pContext) {
        if (gDefualt == null) {
            gDefualt = new ScreenManager(pContext.getApplicationContext());
        }
        return gDefualt;
    }

    private ScreenManager(Context pContext) {
        this.mContext = pContext;
    }

    public void setActivity(AppCompatActivity pActivity) {
        MyApplication.mLiveActivity = (LiveActivity) pActivity;
        mActivityWref = new WeakReference<Activity>(pActivity);
    }

    public void startActivity() {
        LiveActivity.actionToLiveActivity(mContext);
    }

    public void finishActivity() {
        //结束掉LiveActivity
//        if (MyApplication.mLiveActivity != null) {
        if (mActivityWref != null) {
            Activity activity = mActivityWref.get();
            if (activity != null) {
                System.out.println("finish:");
                MyApplication.mLiveActivity.finish();
            }
        }
    }
}