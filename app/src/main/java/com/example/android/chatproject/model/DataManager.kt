package com.example.android.chatproject.model

import android.util.Log
import com.example.android.chatproject.RetrofitInstance
import com.example.android.chatproject.model.api.ApiService
import com.example.android.chatproject.model.request.LoginRequest
import com.example.android.chatproject.model.response.LoginReponse
import io.reactivex.Observable

class DataManager() {
    private val TAG = DataManager::class.simpleName
    private var mService = RetrofitInstance.retrofitInstance?.create(ApiService::class.java)
    fun login(loginRequest: LoginRequest): Observable<LoginReponse>{
        return mService?.let {
            it.login(loginRequest).map {
                var body: LoginReponse? = null
                if (it.response()!!.isSuccessful) {
                    body = it.response()!!.body()
                } else {
                    Log.d(TAG, "ERROR ")
                }

                body!!
            }
        }!!
    }
    companion object {
        @JvmStatic
        fun newInstance() = DataManager()
    }
}