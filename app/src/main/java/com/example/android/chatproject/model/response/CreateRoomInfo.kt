package com.example.android.chatproject.model.response

import com.google.gson.annotations.SerializedName

data class CreateRoomInfo(@SerializedName("room") val room: CreateRoomData)