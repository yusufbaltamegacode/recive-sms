package com.example.listensms

import android.R
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.IBinder
import android.os.PowerManager
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import java.lang.Exception


class MyService : Service() {
    private val NOTIF_ID = 1
    private val NOTIF_CHANNEL_ID = "Channel_Id"

    val smsManager : SmsManager = SmsManager()

    override fun onBind(p0: Intent?): IBinder? {

        return TODO("Provide the return value")
    }

    override fun onCreate() {
        val intent = Intent(applicationContext, this.javaClass)
        val pckageName = packageName;
        intent.setPackage(pckageName)
        val pmManager : PowerManager = getSystemService(POWER_SERVICE) as PowerManager;
        if(!pmManager.isIgnoringBatteryOptimizations(packageName)){
            intent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
        }
        intent.data = Uri.parse("package$pckageName")

        startService(intent)
        super.onCreate()
    }
    override fun onTaskRemoved(rootIntent: Intent?) {
        val restartServiceIntent = Intent(applicationContext, this.javaClass)
        val pckageName = packageName;
        restartServiceIntent.setPackage(pckageName)
        val pmManager : PowerManager = getSystemService(POWER_SERVICE) as PowerManager;
        if(!pmManager.isIgnoringBatteryOptimizations(packageName)){
            restartServiceIntent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
        }




        startService(restartServiceIntent)
        super.onTaskRemoved(rootIntent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        onTaskRemoved(intent)
        registerReceiver(smsManager, IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        startForeground()
        return START_STICKY
    }

    private fun startForeground() {
        try {
            val notificationIntent = Intent(this, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                this, 0,
                notificationIntent, PendingIntent.FLAG_IMMUTABLE,
            )
            startForeground(
                NOTIF_ID, NotificationCompat.Builder(
                    this,
                    NOTIF_CHANNEL_ID
                ) // don't forget create a notification channel first
                    .setOngoing(true)

                    .setContentTitle("deneme")
                    .setContentText("Service is running background")
                    .setContentIntent(pendingIntent)
                    .build()
            )

        } catch (e: Exception){
          println(e)
        }

    }


}