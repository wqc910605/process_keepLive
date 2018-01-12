package com.test.zysnail.www.ui.common;



import okhttp3.Call;

/**
 * 主页
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, OnRefreshListener {

   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("我又活了");
        keepLiveService();
        
    }
    //进程保活
    private void keepLiveService() {
//        startService(new Intent(this, KeepLiveService.class));
        startService(new Intent(this, LiveService.class));
        startService(new Intent(this, MyJobService.class));
        startService(new Intent(this, LiveService2.class));
        final ScreenManager screenManager = ScreenManager.getInstance(MainActivity.this);
        ScreenBroadcastListener listener = new ScreenBroadcastListener(this);
        listener.registerListener(new ScreenBroadcastListener.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                System.out.println("demo:on");
                screenManager.finishActivity();
            }

            @Override
            public void onScreenOff() {
                System.out.println("demo:off");
                screenManager.startActivity();
            }
        });
        sendBroadcast();
    }

    private void sendBroadcast() {
        Intent intent = new Intent();
        Context c = null;
        try {
            c = createPackageContext("com.test.zysnail.www", Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//            intent.setPackage(getPackageName());
//            intent.setComponent(pkgName, className);
//            intent.setComponent(pkgNameContext, className);
        intent.setClassName(c, "com.test.zysnail.www.process_keeplive.TestBroadcastReceiver");
//            intent.setClassName("com.example.broadcasttest", "com.example.broadcasttest.TestBroadcastReceiver");
        intent.setAction("my.broadcast.test");
        intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }
}
