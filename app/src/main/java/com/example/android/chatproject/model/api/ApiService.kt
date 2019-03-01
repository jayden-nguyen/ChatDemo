package com.example.android.chatproject.model.api

import com.example.android.chatproject.model.request.CreateRoomRequest
import com.example.android.chatproject.model.request.LoginRequest
import com.example.android.chatproject.model.response.CreateRoomResponse
import com.example.android.chatproject.model.response.LoginReponse
import com.example.android.chatproject.model.response.RoomResponse
import com.example.android.chatproject.model.response.UserProfileResponse
import com.example.android.chatproject.util.API
import com.example.android.chatproject.util.API.CREATE_ROOM
import com.example.android.chatproject.util.API.GET_CHAT_ROOMS
import com.example.android.chatproject.util.API.GET_USER_LIST
import com.example.android.chatproject.util.API.LOGIN
import com.example.android.chatproject.util.API.REFRESH_TOKEN
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.*

interface ApiService {

    @POST(LOGIN)
    fun login(@Body loginRequest: LoginRequest): Observable<Result<LoginReponse>>

    @GET(GET_USER_LIST)
    fun getUserList(): Observable<Result<UserProfileResponse>>

    @GET(GET_CHAT_ROOMS)
    fun getChatRooms(@Query("page") page: Int, @Query("size") size: Int): Observable<Result<RoomResponse>>

    @POST(CREATE_ROOM)
    fun createRoom(@Body createRoomRequest: CreateRoomRequest): Observable<Result<CreateRoomResponse>>

    @GET(REFRESH_TOKEN)
    fun refreshToken(): Observable<Result<LoginReponse>>
}