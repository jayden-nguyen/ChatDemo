package com.example.android.chatproject.view

import com.example.android.chatproject.contract.MainContract
import com.example.android.chatproject.model.response.CreateRoomData
import com.example.android.chatproject.model.response.RoomData
import com.example.android.chatproject.model.response.UserProfileItem

interface MainView: MainContract.BaseView{
    fun renderUserList(userList: ArrayList<UserProfileItem>?)
    fun renderRoomList(roomList: ArrayList<RoomData>?)
    fun renderCreateRoom(createRoomData: CreateRoomData?)
    fun renderRefreshToken(accessToken: String?)
}