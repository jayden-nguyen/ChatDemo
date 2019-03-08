package com.example.android.chatproject.model.response

import com.google.gson.annotations.SerializedName

data class RoomInfo(@SerializedName("room") val room: ArrayList<RoomData>)