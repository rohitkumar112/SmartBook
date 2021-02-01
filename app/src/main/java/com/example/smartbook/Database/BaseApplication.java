package com.example.smartbook.Database;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import java.util.Calendar;

public class BaseApplication extends Application {

//    private DBHELPER dbHelper;
//
//    public void setContext(Context context) {
//        this.context = context;
//    }
//
//    private Context context;
//    private static BaseApplication instance;
//    public static BaseApplication getInstance() {
//        if (instance == null) {
//            instance = new BaseApplication();
////            Log.d("TAGGG", "INSTANCE CREATED");
//        }
//        return instance;
//    }
//    public Context getContext() {
//        if (context == null) {
//            context = this;
//        }
//        return context;
//    }
//    public DBHELPER getDbHelper() {
//        if (dbHelper == null) {
//            dbHelper = DBHELPER.getInstance(this);
//        }
//        return dbHelper;
//    }
//    public void onCreate() {
//        super.onCreate();
//        instance = this;
//        FixCursorWindow.fix();
//        SharedPreferences sharedPref;
//        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//        boolean isRemember = sharedPref.getBoolean(AppConstants.IsRemember, false);
//
//        WorkerUtils.startPeriodicDataSyncWorker(getApplicationContext());
//        WorkerUtils.startColorUpdateWorker(getApplicationContext(),"");
//
//        if (isRemember) {
////            new Utility().alarmTrigger(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), 3, 0, 1, getApplicationContext(), -1);    //1 for scheduler
//            new Utility().alarmTrigger(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), 25, 8, 0, 6, getApplicationContext(), -1);       ///6 for 25 of every month
////            Log.d("taggg", "onCreate: chaaaaaaaaaaaa");
//        }
////        if (BuildConfig.DEBUG) {
////            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
////                    .detectLeakedSqlLiteObjects()
////                    .detectLeakedClosableObjects()
////                    .penaltyLog()
////                    .penaltyDeath()
////                    .build());
////        }
//    }
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(LocaleHelper.onAttach(base));
//    }
//
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        LocaleHelper.changeLanguageToSelected(this);
//    }
}
