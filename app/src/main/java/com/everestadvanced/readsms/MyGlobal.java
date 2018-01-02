package com.everestadvanced.readsms;

import android.app.Application;

/**
 * Created by Administrator on 12-Sep-17.
 */

public class MyGlobal extends Application
{
    public static String smsdata;

    public void setsmsdata(String smsdat)
    {
        smsdata=smsdat;
    }
    public String getsmsdata()
    {
        return smsdata;
    }
}
