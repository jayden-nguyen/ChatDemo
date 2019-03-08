package com.example.android.chatproject.presenter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.android.chatproject.contract.MainContract
import com.example.android.chatproject.contract.ParentPresenter
import com.example.android.chatproject.model.DataManager
import com.example.android.chatproject.model.request.CreateRoomRequest
import com.example.android.chatproject.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(context: Context): ParentPresenter<MainView>() {
    private var TAG = MainPresenter::class.simpleName
    private var mDataManager = DataManager.newInstance(context)

    @SuppressLint("CheckResult")
    fun getUserList() {
        mDataManager.getUserList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getView()?.renderUserList(it.responseData?.users)
            },{
                Log.d(TAG, "Onrror ${it.message}")
            })
    }

    fun getRooms(page: Int = 0, size: Int = 0) {
        mDataManager.getRooms(page, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getView()?.renderRoomList(it.responseData?.room)
            }, {
                Log.d(TAG, "Onrror ${it.message}")
            })
    }

    fun createRoom(createRoomRequest: CreateRoomRequest, partnerName: String) {
        mDataManager.createRoom(createRoomRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getView()?.renderCreateRoom(it.responseData?.room, partnerName)
            },{
                Log.d(TAG, "Onrror ${it.message}")
            })
    }

    fun refreshToken() {
        mDataManager.refreshToken()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getView()?.renderRefreshToken(it.responseData?.accessToken)
            },{
                Log.d(TAG, "Onrror ${it.message}")
            })
    }
}