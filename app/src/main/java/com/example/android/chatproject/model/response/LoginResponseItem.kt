package com.example.android.chatproject.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponseItem(@SerializedName("expireTime") val expireTime: Long,
                             @SerializedName("accessToken") val accessToken: String,
                             @SerializedName("user") val user: UserLoginInfo,
                             @SerializedName("refreshToken") val refreshToken: String)