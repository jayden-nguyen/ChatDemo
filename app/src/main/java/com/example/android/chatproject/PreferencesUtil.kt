package com.example.android.chatproject

import android.content.Context
import android.content.SharedPreferences

class PreferencesUtil(private val context: Context) {
    lateinit var mPref: SharedPreferences
    lateinit var mEditor: SharedPreferences.Editor
    init {
        mPref = context.getSharedPreferences(context.getString(R.string.preference_name),Context.MODE_PRIVATE)
        mEditor = mPref.edit()
    }

    fun setUserId(userId: Int) {
        mEditor.putInt(context.getString(R.string.user_id), userId)
        mEditor.commit()
    }

    fun getUserId(): Int {
        return mPref.getInt(context.getString(R.string.user_id),0)
    }
}