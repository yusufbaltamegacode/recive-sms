package com.example.listensms

import android.Manifest
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Telephony
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat

class SmsManager : BroadcastReceiver() {



   fun  checkPermission(context: Context,  activity: Activity){
        if(ActivityCompat.checkSelfPermission(context , Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions( activity , arrayOf(Manifest.permission.RECEIVE_SMS,Manifest.permission.SEND_SMS , Manifest.permission.READ_SMS), 111)
            Toast.makeText(context, "izin istendi", Toast.LENGTH_SHORT,).show()
        }else{

        }

   }
    fun onRequestPermissionResult(requestCode : Int, grantResult : IntArray, context:Context){

        if(requestCode===111 && grantResult[0]==PackageManager.PERMISSION_GRANTED){

        }
    }



    override fun onReceive(p0: Context?, p1: Intent?) {
           if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT){

               for (sms in Telephony.Sms.Intents.getMessagesFromIntent(p1)){

                   var comingSms= sms.displayMessageBody

               }
           }
    }
}