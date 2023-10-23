package com.example.listensms

import android.content.Context
import android.util.Log

class LocaleStorageManager {


    fun addValueToDb (key:String , value:String, context :Context ){
        val sharedPreferences = context.getSharedPreferences("a",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()


        editor.putString("key", value)

        editor.apply();

    }

    fun readValue (key:String , context :Context ){
        val sharedPreferences = context.getSharedPreferences("a",Context.MODE_PRIVATE)
        val editor = sharedPreferences.getString("key","key") ?: return;

        Log.d("deneme", editor)



    }
}