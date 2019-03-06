package com.example.android.chatproject.util

object KeyPreferences {
    const val USER_ID = "user_id"
    const val ACCESS_TOKEN = "access_token"
    const val REFRESH_TOKEN = "refresh_token"
    const val EXPIRED_TIME = "expire_time"
    const val USER_NAME = "user_name"
}

object ResponseCode {
    const val SUCCESS = 200
    const val UNAUTHORLIZED = 401
}

object API{
    const val LOGIN = "login"
    const val GET_USER_LIST = "user/list"
    const val GET_CHAT_ROOMS = "room"
    const val CREATE_ROOM = "room"
    const val REFRESH_TOKEN = "auth"
}

object RoomKey {
    const val ROOM_ID = "room_id"
    const val ROOM_USER_LIST = "room_userids"
    const val ROOM_NAME = "room_name"
    const val ROOM_PARTNER_NAME = "room_partner_name"
}

object DateFormat {
    const val BIRTHDAY_FORMAT = "yyyy-MM-dd"
}