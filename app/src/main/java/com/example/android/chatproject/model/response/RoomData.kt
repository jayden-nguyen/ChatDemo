package com.example.android.chatproject.model.response

import com.google.gson.annotations.SerializedName

data class RoomData(@SerializedName("id") val id: Int,
                    @SerializedName("name") val name: String,
                    @SerializedName("userIds") val userIds: ArrayList<Int>)