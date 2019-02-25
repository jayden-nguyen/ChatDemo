package com.example.android.chatproject.model.response

import com.google.gson.annotations.SerializedName

data class UserProfileItem(
    @SerializedName("id") val id: Int,
    @SerializedName("userName") val userName: String,
    @SerializedName("createTime") val createTime: Long,
    @SerializedName("userInfo") val userInfo: UserInfo,
    @SerializedName("status") val status: Int
)