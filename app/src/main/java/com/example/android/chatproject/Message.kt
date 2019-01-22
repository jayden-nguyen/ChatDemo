package com.example.android.chatproject

import com.google.gson.annotations.SerializedName

data class Message(@SerializedName("userId") val userId: Int,
                   @SerializedName("userName") val userName: String,
                   @SerializedName("message") val message: String)