package com.example.android.chatproject.presenter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.android.chatproject.contract.MainContract
import com.example.android.chatproject.contract.ParentPresenter
import com.example.android.chatproject.model.DataManager
import com.example.android.chatproject.model.request.LoginRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginPresenter(context: Context):ParentPresenter<MainContract.BaseView>() {

    private var mDataManager = DataManager.newInstance(context)
    private var TAG = LoginPresenter::class.simpleName

    @SuppressLint("CheckResult")
    fun login(loginRequest: LoginRequest) {
       mDataManager.login(loginRequest)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe({
               getView()?.renderLoginResult(it.responseData)
           }, {
               Log.d(TAG, "Error ${it.message}")
           })
    }
}