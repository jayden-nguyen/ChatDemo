package com.example.android.chatproject.model

import android.util.Log
import com.example.android.chatproject.util.RetrofitInstance
import com.example.android.chatproject.model.api.ApiService
import com.example.android.chatproject.model.request.LoginRequest
import com.example.android.chatproject.model.response.LoginReponse
import com.example.android.chatproject.model.response.UserProfileResponse
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

    fun getUserList(): Observable<UserProfileResponse> {
        return mService?.let {
            it.getUserList().map {
                var body: UserProfileResponse? = null
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