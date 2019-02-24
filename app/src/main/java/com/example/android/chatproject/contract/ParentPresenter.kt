package com.example.android.chatproject.contract

import io.reactivex.disposables.CompositeDisposable

open class ParentPresenter<V>: MainContract.BasePresenter<V>{
    private var mView:V? = null
    private var mCompositeDisposable = CompositeDisposable()

    override fun setView(view: V) {
        mView = view
    }

    override fun removeView() {
        mView = null
        mCompositeDisposable.dispose()
    }

    fun getView() = mView
}