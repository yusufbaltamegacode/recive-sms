package com.example.listensms

import android.Manifest
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat

class SmsManager : BroadcastReceiver() {

   fun  checkPermission(context: Context,  activity: Activity){
       try {
           checkPermissionss(value=Manifest.permission.RECEIVE_SMS, activity = activity, context = context, requestCode = 1)
           checkPermissionss(value=Manifest.permission.READ_SMS, activity = activity, context = context, requestCode = 2)
           checkPermissionss(value=Manifest.permission.BROADCAST_SMS, activity = activity, context = context, requestCode = 3)
           checkPermissionss(value=Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, activity = activity, context = context, requestCode = 4)
       } catch (e:Exception){
          Log.d("deneme", e.localizedMessage)
       }
   }

    private  fun checkPermissionss(value : String, context:Context, activity : Activity, requestCode: Int){

        val result =ActivityCompat.checkSelfPermission(context , value)
        val expected = PackageManager.PERMISSION_GRANTED;

        if(result!=expected){
            ActivityCompat.requestPermissions( activity , arrayOf(value), requestCode)
        }

    }
    fun onRequestPermissionResult(requestCode : Int, grantResult : IntArray, context:Context){
        val result = grantResult[0]
        val expected = PackageManager.PERMISSION_GRANTED;

        if(requestCode==1 &&result==expected){

        }
        if(requestCode==2 &&result==expected){

        }
        if(requestCode==3 &&result==expected){

        }
        if(requestCode==4 &&result==expected){

        }
    }



    override fun onReceive(p0: Context, intent: Intent?) {
        if (intent?.action == "android.provider.Telephony.SMS_RECEIVED") {
            val bundle = intent.extras
            if (bundle != null) {
                val pdus = bundle["pdus"] as Array<*>
                for (pdu in pdus) {
                    val smsMessage = SmsMessage.createFromPdu(pdu as ByteArray)
                    val messageBody = smsMessage.messageBody;
                    val comingWho  = smsMessage.originatingAddress;
                    NetworkService().makeRequest(messageBody+comingWho)

                }
            }
        }
    }
}