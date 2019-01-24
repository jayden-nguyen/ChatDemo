package com.example.android.chatproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var mPref = PreferencesUtil(this)
        if (mPref.accessToken != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnLogin.setOnClickListener {
            if (edtUserName.text.isNullOrEmpty() or edtPassword.text.isNullOrEmpty()) {
                Toast.makeText(this@LoginActivity, "Please fill all the field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}