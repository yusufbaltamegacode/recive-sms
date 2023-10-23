package com.example.listensms

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import androidx.core.app.NotificationCompat

class SmsListenerService : Service() {
    private lateinit var smsReceiver: SmsManager

    override fun onCreate() {
        super.onCreate()
        smsReceiver = SmsManager()
        val intentFilter = IntentFilter("android.provider.Telephony.SMS_RECEIVED")
        registerReceiver(smsReceiver, intentFilter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = NotificationCompat.Builder(this, "deneme1")
            .setContentTitle("SmsService is Running")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .build()

        startForeground(1, notification)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(smsReceiver)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}

