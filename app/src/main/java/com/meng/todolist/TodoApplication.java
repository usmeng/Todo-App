package com.meng.todolist;

import android.app.Application;
import android.content.Context;

/**
 * Created by mengzhou on 8/10/17.
 */

public class TodoApplication extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getApplicatioContext(){
        return appContext;
    }
}
