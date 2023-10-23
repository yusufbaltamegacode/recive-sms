package com.example.listensms

import android.util.Log
import com.example.listensms.model.Contents
import com.example.listensms.model.OneSignalSendModel
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import java.io.IOException


class NetworkService {

    private val client = OkHttpClient()

    fun makeRequest(message:String) {
        try {

            val  model  :OneSignalSendModel = OneSignalSendModel(
                app_id ="054a567a-a804-4bc8-87c8-fda2dec7ff83" ,
                contents= Contents(en = message),
                mutable_content = true,
                include_player_ids = listOf("8c9e5fc0-3c3e-412f-bbdb-fc20fdd26367"),
                small_icon = "ic_notification_icon",
                included_segments = listOf("included_player_ids"),
                title = message,
                content_available = true,
                data = null,
                );

            var result = Gson().toJson(model).toRequestBody()






            val request = Request.Builder().url("https:onesignal.com/api/v1/notifications")
                .post(result)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader(
                    "Authorization", "Basic OGM4MWQxY2QtYWMxZS00NDc3LTg0NjctYzJhYjMwZmZkOWFh"
                )
                .addHeader("accept","*/*")
//accept: */*
                .build()


            Thread {
                try {
                    client.newCall(request).execute().use { response ->
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")
                        Log.d("deneme",response.body!!.string())

                    }
                } catch (ex: Exception) {
                    Log.d("deneme", ex.localizedMessage)
                }
            }.start()

        } catch (e: Exception) {
            Log.d("deneme", e.localizedMessage)
        }


    }
}



interface OneSignalApi {


    @Headers(
        "Content-Type : application/json; charset=utf-8",
        "Authorization : Basic OGM4MWQxY2QtYWMxZS00NDc3LTg0NjctYzJhYjMwZmZkOWFh"
    )
    @POST("/api/v1/notifications")
    fun sendMessage(): Response<Any>
}

