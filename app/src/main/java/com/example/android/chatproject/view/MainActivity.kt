package com.example.android.chatproject.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import android.widget.Toast
import com.example.android.chatproject.util.PreferencesUtil
import com.example.android.chatproject.R
import com.example.android.chatproject.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mPref: PreferencesUtil
    private lateinit var mPagerAdapter: MainAdapter
    private var isCalledApi = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager()
        //
        mPref = PreferencesUtil(this)
        Toast.makeText(this, "AccessToken: ${mPref.accessToken}", Toast.LENGTH_SHORT).show()

        if (mPref.accessToken == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun setupViewPager() {
        mPagerAdapter = MainAdapter(supportFragmentManager)
        mPagerAdapter.addFragment(RoomListFragment.newInstance())
        mPagerAdapter.addFragment(UserListFragment.newInstance())
        mainViewPager.adapter = mPagerAdapter
        mainTabs.setupWithViewPager(mainViewPager)
    }
}
