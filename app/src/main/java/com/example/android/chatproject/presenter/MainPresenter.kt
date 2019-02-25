package com.example.android.chatproject.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.example.android.chatproject.contract.MainContract
import com.example.android.chatproject.contract.ParentPresenter
import com.example.android.chatproject.model.DataManager
import com.example.android.chatproject.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter: ParentPresenter<MainView>() {
    private var TAG = MainPresenter::class.simpleName
    private var mDataManager = DataManager.newInstance()

    @SuppressLint("CheckResult")
    fun getUserList() {
        mDataManager.getUserList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getView()?.renderUserList(it.responseData)
            },{
                Log.d(TAG, "Onrror ${it.message}")
            })
    }
}