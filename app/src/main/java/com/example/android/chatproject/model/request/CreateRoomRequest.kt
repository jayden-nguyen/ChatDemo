package com.example.android.chatproject.model.request

import com.google.gson.annotations.SerializedName

data class CreateRoomRequest(@SerializedName("userIds") val userIds: List<Int>,
                             @SerializedName("roomName") val roomName: String)