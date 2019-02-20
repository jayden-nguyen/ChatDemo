package com.example.android.chatproject.presenter

import android.annotation.SuppressLint
import android.provider.ContactsContract
import android.util.Log
import com.example.android.chatproject.LoginActivity
import com.example.android.chatproject.RetrofitInstance
import com.example.android.chatproject.contract.MainContract
import com.example.android.chatproject.model.DataManager
import com.example.android.chatproject.model.api.ApiService
import com.example.android.chatproject.model.request.LoginRequest
import com.example.android.chatproject.model.response.LoginReponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginPresenter: MainContract.BasePresenter<MainContract.BaseView> {

    private var mDataManager = DataManager.newInstance()
    private var mView: MainContract.BaseView? = null
    private var mCompositeDisposable = CompositeDisposable()
    private var TAG = LoginPresenter::class.simpleName


    fun setView(view: MainContract.BaseView) {
        mView = view
    }


    fun removeView() {
        mView = null
        mCompositeDisposable.dispose()
    }

    @SuppressLint("CheckResult")
    fun login(loginRequest: LoginRequest) {
       mDataManager.login(loginRequest)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe({
               mView?.renderLoginResult(it.responseData)
           }, {
               Log.d(TAG, "Error ${it.message}")
           })
    }




    companion object {
        @JvmStatic
        fun newInstance() = LoginPresenter()
    }

}