package com.example.android.chatproject.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.android.chatproject.util.PreferencesUtil
import com.example.android.chatproject.R
import com.example.android.chatproject.contract.MainContract
import com.example.android.chatproject.model.request.LoginRequest
import com.example.android.chatproject.model.response.LoginResponseItem
import com.example.android.chatproject.presenter.LoginPresenter
import com.example.android.chatproject.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() , LoginView{

    private var mPresenter: LoginPresenter? = null
    lateinit var mPref: PreferencesUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Create new Presenter
        if (mPresenter == null) {
            mPresenter = LoginPresenter(this)
        }
        if (mPresenter != null) {
            mPresenter!!.setView(this)
        }

        //
         mPref = PreferencesUtil(this)

        btnLogin.setOnClickListener {
            if (edtUserName.text.isNullOrEmpty() or edtPassword.text.isNullOrEmpty()) {
                Toast.makeText(this@LoginActivity, "Please fill all the field", Toast.LENGTH_SHORT).show()
            } else {
                mPresenter!!.login(LoginRequest(edtUserName.text.toString(),edtPassword.text.toString()))
            }
        }
    }

    override fun renderLoginResult(item: LoginResponseItem?) {
        Toast.makeText(this@LoginActivity, "Success ${item?.user}", Toast.LENGTH_SHORT).show()
        mPref.accessToken = item?.accessToken
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }

    override fun onDestroy() {
        mPresenter?.removeView()
        super.onDestroy()
    }
}