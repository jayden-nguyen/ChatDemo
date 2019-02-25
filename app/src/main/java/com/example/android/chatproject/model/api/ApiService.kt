package com.example.android.chatproject.model.api

import com.example.android.chatproject.model.request.LoginRequest
import com.example.android.chatproject.model.response.LoginReponse
import com.example.android.chatproject.model.response.UserProfileResponse
import com.example.android.chatproject.util.API
import com.example.android.chatproject.util.API.GET_USER_LIST
import com.example.android.chatproject.util.API.LOGIN
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST(LOGIN)
    fun login(@Body loginRequest: LoginRequest): Observable<Result<LoginReponse>>

    @GET(GET_USER_LIST)
    fun getUserList(): Observable<Result<UserProfileResponse>>
}