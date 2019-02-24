package com.example.android.chatproject.presenter

import com.example.android.chatproject.contract.MainContract
import com.example.android.chatproject.contract.ParentPresenter
import io.reactivex.disposables.CompositeDisposable

class MainPresenter: ParentPresenter<MainContract.BaseView>() {
    private var TAG = MainPresenter::class.simpleName
    
}