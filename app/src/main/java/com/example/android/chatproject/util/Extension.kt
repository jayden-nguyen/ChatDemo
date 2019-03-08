package com.example.android.chatproject.util

import com.example.android.chatproject.util.DateFormat.BIRTHDAY_FORMAT
import java.text.SimpleDateFormat
import java.util.*

fun Date.convertToString(): String {
    return SimpleDateFormat(BIRTHDAY_FORMAT).format(this)
}

