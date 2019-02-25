package com.example.android.chatproject.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(@SerializedName("userName") val userName: String,
                        @SerializedName("password") val password: String)