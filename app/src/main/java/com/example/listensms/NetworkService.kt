package com.example.listensms

import android.util.Log
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import java.io.IOException


class NetworkService {

    private val client = OkHttpClient()

    fun makeRequest() {
        try {
            val contents = mapOf("en" to "deneme")
            val json = """
 {"app_id":"054a567a-a804-4bc8-87c8-fda2dec7ff83","contents":{"en":"description"},"mutable_content":true,"title":"headline6","included_segments":["included_player_ids"],"include_player_ids":["ec73a6c3-ace8-438d-a465-826d48621f8e"],"content_available":true,"small_icon":"ic_notification_icon","data":null}
}
""".trimIndent()

           val body1= RequestBody.create("application/json".toMediaTypeOrNull(), json)





            val request = Request.Builder().url("https://192.168.1.38:7281/category/get-categorys")
                .get()
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

                        println(response.body!!.string())
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

