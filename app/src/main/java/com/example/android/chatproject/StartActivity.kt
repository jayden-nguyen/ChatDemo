package com.example.android.chatproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.example.android.chatproject.ui.LoginActivity
import com.example.android.chatproject.ui.MainActivity
import com.example.android.chatproject.util.PreferencesUtil

class StartActivity: AppCompatActivity() {

    private lateinit var mPref: PreferencesUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        mPref = PreferencesUtil(this)
        Toast.makeText(this, "AccessToken: ${mPref.accessToken}", Toast.LENGTH_SHORT).show()

        if (mPref.accessToken == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}