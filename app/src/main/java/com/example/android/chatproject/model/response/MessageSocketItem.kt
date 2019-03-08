package com.example.android.chatproject.model.response

import com.google.gson.annotations.SerializedName

data class MessageSocketItem(@SerializedName("message") val message: String,
                             @SerializedName("fromUserId") val fromUserId: Int,
                             @SerializedName("fromUserName") val fromUserName: String,
                             @SerializedName("toUserId") val toUserId: Int,
                             @SerializedName("toUserName") val toUserName: String)