package com.example.android.chatproject.presenter

import android.util.Log
import com.example.android.chatproject.LoginActivity
import com.example.android.chatproject.RetrofitInstance
import com.example.android.chatproject.contract.MainContract
import com.example.android.chatproject.model.api.ApiService
import com.example.android.chatproject.model.request.LoginRequest
import com.example.android.chatproject.model.response.LoginReponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginPresenter: MainContract.BasePresenter<MainContract.BaseView> {


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

    fun login(loginRequest: LoginRequest) {
        RetrofitInstance.retrofitInstance?.let {
            it.create(ApiService::class.java)
                .login(loginRequest).map {
                    var body: LoginReponse? = null
                    if (it.response()!!.isSuccessful) {
                        body = it.response()!!.body()
                    } else {
                        Log.d(TAG, "ERROR ")
                    }

                    body!!
                }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                    mView?.renderLoginResult(it.responseData)
                }, {
                    Log.d(TAG, "Error Connect ${it.message}")
                })
        }?.let {
            mCompositeDisposable.add(
                it
            )
        }
    }



    companion object {
        @JvmStatic
        fun newInstance() = LoginPresenter()
    }

}