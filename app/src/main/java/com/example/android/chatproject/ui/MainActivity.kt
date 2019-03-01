package com.example.android.chatproject.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android.chatproject.util.PreferencesUtil
import com.example.android.chatproject.R
import com.example.android.chatproject.model.response.CreateRoomData
import com.example.android.chatproject.model.response.RoomData
import com.example.android.chatproject.model.response.UserProfileItem
import com.example.android.chatproject.presenter.MainPresenter
import com.example.android.chatproject.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , MainView{

    private lateinit var mPagerAdapter: MainAdapter
    private var isCalledApi = true
    private lateinit var mPref: PreferencesUtil
    private var mPresenter: MainPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPref = PreferencesUtil(this)
        setupViewPager()
        if (mPresenter == null) {
            mPresenter = MainPresenter(this)
            mPresenter?.setView(this)
        }
        checkExpiredTime()
    }

    private fun checkExpiredTime() {
        if (System.currentTimeMillis() > mPref.expiredTime!!) {
            mPresenter?.refreshToken()
        }
    }

    private fun setupViewPager() {
        mPagerAdapter = MainAdapter(supportFragmentManager)
        mPagerAdapter.addFragment(RoomListFragment.newInstance())
        mPagerAdapter.addFragment(UserListFragment.newInstance())
        mainViewPager.adapter = mPagerAdapter
        mainTabs.setupWithViewPager(mainViewPager)
    }

    override fun renderUserList(userList: ArrayList<UserProfileItem>?) {

    }

    override fun renderRoomList(roomList: ArrayList<RoomData>?) {

    }

    override fun renderCreateRoom(createRoomData: CreateRoomData?) {

    }

    override fun renderRefreshToken(accessToken: String?) {
        mPref.accessToken = accessToken
    }

    override fun onDestroy() {
        mPresenter?.removeView()
        super.onDestroy()
    }
}
