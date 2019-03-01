package com.example.android.chatproject.model

import android.content.Context
import android.util.Log
import com.example.android.chatproject.util.RetrofitInstance
import com.example.android.chatproject.model.api.ApiService
import com.example.android.chatproject.model.request.CreateRoomRequest
import com.example.android.chatproject.model.request.LoginRequest
import com.example.android.chatproject.model.response.CreateRoomResponse
import com.example.android.chatproject.model.response.LoginReponse
import com.example.android.chatproject.model.response.RoomResponse
import com.example.android.chatproject.model.response.UserProfileResponse
import io.reactivex.Observable

class DataManager(private val mContext: Context) {
    private val TAG = DataManager::class.simpleName
    private var mService = RetrofitInstance(mContext).retrofitInstance?.create(ApiService::class.java)
    private var mServiceSocket = RetrofitInstance(mContext).socketHostInstance?.create(ApiService::class.java)
    private var mServiceRefresh = RetrofitInstance(mContext).refreshInstance?.create(ApiService::class.java)
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

    fun getRooms(page:Int, size:Int): Observable<RoomResponse> {
        return mServiceSocket?.let {
            it.getChatRooms(page, size).map {
                var body: RoomResponse? = null
                if (it.response()!!.isSuccessful) {
                    body = it.response()!!.body()
                } else {
                    Log.d(TAG, "ERROR ")
                }
                body!!
            }
        }!!
    }

    fun createRoom(createRoomRequest: CreateRoomRequest): Observable<CreateRoomResponse> {
        return mServiceSocket?.let {
            it.createRoom(createRoomRequest).map {
                var body: CreateRoomResponse? = null
                if (it.response()!!.isSuccessful) {
                    body = it.response()!!.body()
                } else {
                    Log.d(TAG, "ERROR ")
                }
                body!!
            }
        }!!
    }

    fun refreshToken(): Observable<LoginReponse> {
        return mServiceRefresh?.let {
            it.refreshToken().map {
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
        fun newInstance(context: Context) = DataManager(context)
    }
}