package com.example.android.chatproject.model.response

import com.google.gson.annotations.SerializedName

data class UserInfo(@SerializedName("userId") val userId: Int,
                    @SerializedName("gender") val gender: String,
                    @SerializedName("dob") val dob: Long,
                    @SerializedName("philosophy") val philosophy: String)