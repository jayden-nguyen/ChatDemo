package com.example.android.chatproject.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android.chatproject.R
import com.example.android.chatproject.model.response.LoginResponseItem
import com.example.android.chatproject.model.response.UserProfileItem
import com.example.android.chatproject.presenter.MainPresenter
import com.example.android.chatproject.view.MainView
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFragment: Fragment() , MainView{
    private val TAG = UserListFragment::class.simpleName
    private lateinit var mAdapter: UserListAdapter
    override fun renderUserList(userList: ArrayList<UserProfileItem>?) {
        if (userList != null) {
            mAdapter.addUserList(userList)
        }
    }

    override fun renderRoomList() {

    }

    override fun renderLoginResult(item: LoginResponseItem?) {

    }

    private var mPresenter:MainPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mPresenter == null) {
            mPresenter = context?.let { MainPresenter(it) }
            mPresenter?.setView(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_list, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mPresenter?.getUserList()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        rcvUsers.apply {
            mAdapter = UserListAdapter {
                Toast.makeText(context, "Open chat", Toast.LENGTH_SHORT).show()
            }
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    override fun onDestroy() {
        mPresenter?.removeView()
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserListFragment()
    }
}