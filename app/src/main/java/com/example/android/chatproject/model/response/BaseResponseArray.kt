package com.example.android.chatproject.model.response

import com.google.gson.annotations.SerializedName

abstract class BaseResponseArray<V>(@SerializedName("status") val status: Int = 0,
                               @SerializedName("message") val message: String = "",
                               @SerializedName("responseData") val responseData: ArrayList<V>?= null)