package com.example.android.chatproject.view

import com.example.android.chatproject.contract.MainContract
import com.example.android.chatproject.model.response.LoginResponseItem

interface LoginView: MainContract.BaseView {
    fun renderLoginResult(item: LoginResponseItem?)
}