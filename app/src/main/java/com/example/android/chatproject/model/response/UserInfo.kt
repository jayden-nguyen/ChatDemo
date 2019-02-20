package com.example.android.chatproject.model.response

import com.google.gson.annotations.SerializedName

data class UserInfo(@SerializedName("userId") val userId: Int,
                    @SerializedName("userName") val userName: String)