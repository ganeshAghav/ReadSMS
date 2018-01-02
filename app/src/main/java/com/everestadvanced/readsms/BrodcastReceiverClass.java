package com.everestadvanced.readsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by Administrator on 12-Sep-17.
 */

public class BrodcastReceiverClass extends BroadcastReceiver
{
    MyGlobal myGlobal;

    @Override
    public void onReceive(Context context, Intent intent) {

        myGlobal=new MyGlobal();

        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++)
                {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    try
                    {
//                        if (senderNum.equals("+917021855201"))
//                        {  //SMS Provider Name
//                        }
                        if (senderNum.equals("RM-WAYSMS"))
                        {  //SMS Provider Name
                            myGlobal.setsmsdata(message);
                        }


                    } catch (Exception e) {
                    }

                }
            }

        } catch (Exception e) {

        }
    }
}