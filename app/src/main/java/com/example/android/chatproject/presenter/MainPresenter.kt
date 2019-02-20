package com.example.android.chatproject.presenter

import com.example.android.chatproject.contract.MainContract
import io.reactivex.disposables.CompositeDisposable

class MainPresenter: MainContract.BasePresenter<MainContract.BaseView> {
    private var mView: MainContract.BaseView? = null
    private var mCompositeDisposable = CompositeDisposable()
    private var TAG = MainPresenter::class.simpleName

    fun setView(view: MainContract.BaseView) {
        mView = view
    }


    fun removeView() {
        mView = null
        mCompositeDisposable.dispose()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginPresenter()
    }
}