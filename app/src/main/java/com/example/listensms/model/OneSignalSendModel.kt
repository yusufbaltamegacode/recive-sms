package com.example.listensms.model

data class OneSignalSendModel(
    val app_id: String,
    val contents: Contents,
    val mutable_content: Boolean,
    val content_available: Boolean,
    val title: String,
    val included_segments: List<String>,
    val include_player_ids: List<String>,
    val small_icon: String,
    val data : Map<String, Any>?
)



data class  Contents(val en:String)


