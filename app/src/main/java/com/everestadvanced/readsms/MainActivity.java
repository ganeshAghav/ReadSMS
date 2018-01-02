package com.everestadvanced.readsms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    MyGlobal myGlobal;
    Button smsshow;
    private int REQUEST_ID_MULTIPLE_PERMISSIONS = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetPermissionDetails();

        myGlobal=new MyGlobal();
        smsshow= (Button) findViewById(R.id.showsms);
        smsshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(myGlobal.getsmsdata()!=null) {
                    Toast.makeText(MainActivity.this, "Message: " + myGlobal.getsmsdata().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void  GetPermissionDetails() {

        //Check manual permission if andorid version >6.0 runtime permission
        boolean result=CheckPermissionsGranted();

        if(result==true)
        {
            //call for get login details
            // GetLoginDetails();
        }
        else
        {
            //call for runtime permission
            requestPermission();
        }

    }

    //Check all permission are granted ro not granted
    public boolean CheckPermissionsGranted() {

        //this code for multiple permission to check like location and phone state
        int permissionreadsms = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        int receivesmsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        if(permissionreadsms==0 && receivesmsPermission==0)
        {
            //permission is granted
            return true;
        }
        else
        {
            //permission not granted
            return false;
        }
    }

    //Requesting permission
    private void requestPermission() {

        //this code for multiple permission to check like location and phone state
        int permissionreadsms = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        int receivesmsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionreadsms != PackageManager.PERMISSION_GRANTED)
        {
            listPermissionsNeeded.add(Manifest.permission.READ_SMS);
        }
        if (receivesmsPermission != PackageManager.PERMISSION_GRANTED)
        {
            listPermissionsNeeded.add(Manifest.permission.RECEIVE_SMS);
        }
        if (!listPermissionsNeeded.isEmpty())
        {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
        }
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //call for get login details
               // GetLoginDetails();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Oppus you dined permission",Toast.LENGTH_LONG).show();
                MainActivity.this.finish();
            }
        }
    }
}
