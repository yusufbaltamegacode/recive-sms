package com.example.listensms

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    var smsManeger: SmsManager = SmsManager();
    private lateinit var button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        smsManeger.checkPermission(this,this);
        val serviceIntent = Intent(this, SmsListenerService::class.java)
        val serviceIntent1 = Intent(this, MyService::class.java)
     //   this.startService(serviceIntent)

        this.startForegroundService(serviceIntent1)

       button = findViewById(R.id.btn_send_push)
       button.setOnClickListener(View.OnClickListener {

            NetworkService().makeRequest("asdf")
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