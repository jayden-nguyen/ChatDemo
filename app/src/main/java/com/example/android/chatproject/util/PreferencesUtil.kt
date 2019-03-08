package com.example.android.chatproject.util

import android.content.Context
import android.content.SharedPreferences
import com.example.android.chatproject.R
import com.example.android.chatproject.util.KeyPreferences.ACCESS_TOKEN
import com.example.android.chatproject.util.KeyPreferences.EXPIRED_TIME
import com.example.android.chatproject.util.KeyPreferences.REFRESH_TOKEN
import com.example.android.chatproject.util.KeyPreferences.USER_ID
import com.example.android.chatproject.util.KeyPreferences.USER_NAME

class PreferencesUtil(private val context: Context) {
    fun clear() {
        mPref.edit().clear().apply()
    }

    private var mPref = context.getSharedPreferences(context.getString(R.string.preference_name),Context.MODE_PRIVATE)

    var userId: Int
    get() = mPref[USER_ID] ?: 0
    set(value) {
        mPref[USER_ID] = value
    }

    var userName: String
    get() = mPref[USER_NAME] ?: ""
    set(value) {
        mPref[USER_NAME] = value
    }

    var accessToken: String?
    get() = mPref[ACCESS_TOKEN]
    set(value) {
        mPref[ACCESS_TOKEN] = value
    }

    var refreshToken: String?
    get() = mPref[REFRESH_TOKEN]
    set(value) {
        mPref[REFRESH_TOKEN] = value
    }

    var expiredTime: Long?
    get() = mPref[EXPIRED_TIME]
    set(value) {
        mPref[EXPIRED_TIME] = value
    }
}

inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    var editor = this.edit()
    operation(editor)
    editor.apply()
}

operator fun SharedPreferences.set(key: String, value: Any?) {
    when(value) {
        is String? -> edit { it.putString(key, value) }
        is Int -> edit{it.putInt(key, value)}
        is Long -> edit { it.putLong(key, value) }
        is Float -> edit { it.putFloat(key, value) }
        is Boolean -> edit {it.putBoolean(key, value)}
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}

inline operator fun <reified T: Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
    return when (T::class) {
        String::class -> getString(key, defaultValue as? String) as T?
        Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
        Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
        Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}