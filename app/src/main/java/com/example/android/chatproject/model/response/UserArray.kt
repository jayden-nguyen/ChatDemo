package com.example.android.chatproject.model.response

import com.google.gson.annotations.SerializedName

data class UserArray(@SerializedName("users") val users: ArrayList<UserProfileItem>)