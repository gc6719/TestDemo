package com.utils.rekha.waterreminderapplication.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Gururaj on 5/19/2018.
 */
public class UseLib {
    public static  final String spWakeUpTime = "spWakeUpTime";
    public  static  final  String spSleepTime = "spSleepTime";
    public  static  final  String spIntervalTime = "spIntervalTime";

    public void saveString(String key, String val, Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, val);
        editor.apply();

    }
}
