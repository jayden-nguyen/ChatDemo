package com.example.android.chatproject.util

import android.content.Context
import android.content.SharedPreferences
import com.example.android.chatproject.BuildConfig.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance(private val context: Context){
    private var retrofit: Retrofit? = null
    private var mPref = PreferencesUtil(context)
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            it.proceed(it.request().newBuilder()
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization", "Bearer " + mPref.accessToken).build())
        }
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .build()!!
    val retrofitInstance: Retrofit?
    get() {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        return retrofit
    }


}
