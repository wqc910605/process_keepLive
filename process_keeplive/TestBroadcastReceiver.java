package com.test.zysnail.www.process_keeplive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TestBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        /*Toast.makeText(context, "action:" + action, Toast.LENGTH_LONG).show();
        Log.i("TestBroadcastReceiver", "action:" + action);*/
        context.startService(new Intent(context, KeepLiveService.class));
    }
}
