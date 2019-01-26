package com.example.android.chatproject.model.api

import com.example.android.chatproject.model.request.LoginRequest
import com.example.android.chatproject.model.response.LoginReponse
import com.example.android.chatproject.util.API
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST(API.LOGIN)
    fun login(@Body loginRequest: LoginRequest): Observable<Result<LoginReponse>>
}