package com.example.android.chatproject.contract

import com.example.android.chatproject.model.response.LoginResponseItem
import io.reactivex.Observer


class MainContract {
    interface BasePresenter<V> {

    }

    interface BaseView {
        fun renderLoginResult(item: LoginResponseItem?)
    }
}