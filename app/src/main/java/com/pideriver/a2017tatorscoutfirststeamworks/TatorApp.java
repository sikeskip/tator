package com.pideriver.a2017tatorscoutfirststeamworks;

import android.app.Application;
import android.content.Context;

/**
 * Created by David Reetz on 1/5/2017.
 */

public class TatorApp extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        TatorApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return TatorApp.context;
    }
}
