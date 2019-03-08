package com.example.android.chatproject.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android.chatproject.R
import com.example.android.chatproject.model.request.CreateRoomRequest
import com.example.android.chatproject.model.response.CreateRoomData
import com.example.android.chatproject.model.response.LoginResponseItem
import com.example.android.chatproject.model.response.RoomData
import com.example.android.chatproject.model.response.UserProfileItem
import com.example.android.chatproject.presenter.MainPresenter
import com.example.android.chatproject.util.PreferencesUtil
import com.example.android.chatproject.util.RoomKey.ROOM_ID
import com.example.android.chatproject.util.RoomKey.ROOM_NAME
import com.example.android.chatproject.util.RoomKey.ROOM_PARTNER_NAME
import com.example.android.chatproject.util.RoomKey.ROOM_USER_LIST
import com.example.android.chatproject.view.MainView
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFragment: Fragment() , MainView{
    private val TAG = UserListFragment::class.simpleName
    private lateinit var mAdapter: UserListAdapter
    private lateinit var mPref: PreferencesUtil
    override fun renderUserList(userList: ArrayList<UserProfileItem>?) {
        if (userList != null) {
            mAdapter.addUserList(userList)
        }
    }

    override fun renderRoomList(roomList: ArrayList<RoomData>?) {

    }

    override fun renderCreateRoom(createRoomData: CreateRoomData?, partnerName: String) {
        val id = createRoomData?.id
        val userIds = createRoomData?.userIds
        val name = createRoomData?.name
        startActivity(Intent(context, ChatActivity::class.java).apply {
            putExtra(ROOM_ID, id)
            putExtra(ROOM_USER_LIST, userIds)
            putExtra(ROOM_PARTNER_NAME, partnerName)
        })
    }

    override fun renderRefreshToken(accessToken: String?) {

    }


    private var mPresenter:MainPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mPresenter == null) {
            mPresenter = context?.let { MainPresenter(it) }
            mPresenter?.setView(this)
        }
        mPref = PreferencesUtil(context!!)
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
            mAdapter = UserListAdapter (mPref,onClick = {
                userIds,name ->
                onClicked(userIds, name)
            })
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun onClicked(userIds: List<Int>, name: String) {
        mPresenter?.createRoom(CreateRoomRequest(userIds, name), name)
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