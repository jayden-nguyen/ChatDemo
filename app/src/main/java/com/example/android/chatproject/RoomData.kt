package com.example.android.chatproject

import com.google.gson.annotations.SerializedName

data class RoomData(@SerializedName("id") val id: Int,
                    @SerializedName("name") val name: String,
                    @SerializedName("userIds") val userIds: List<Int>,
                    @SerializedName("messages") val messages: List<Message>)