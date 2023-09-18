package com.example.listensms

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    var smsManeger: SmsManager = SmsManager();
    private lateinit var button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS), 123)
        smsManeger.checkPermission(this,this);
        startService(Intent(applicationContext, MyService::class.java))

       button = findViewById(R.id.btn_send_push)
        button.setOnClickListener(View.OnClickListener {

            NetworkService().makeRequest()
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        smsManeger.onRequestPermissionResult(requestCode, grantResults, this)

    }




}