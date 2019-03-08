package com.example.android.chatproject.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.chatproject.R
import com.example.android.chatproject.model.request.CreateRoomRequest
import com.example.android.chatproject.model.response.CreateRoomData
import com.example.android.chatproject.model.response.LoginResponseItem
import com.example.android.chatproject.model.response.RoomData
import com.example.android.chatproject.model.response.UserProfileItem
import com.example.android.chatproject.presenter.MainPresenter
import com.example.android.chatproject.util.PreferencesUtil
import com.example.android.chatproject.util.RoomKey
import com.example.android.chatproject.view.MainView
import kotlinx.android.synthetic.main.fragment_room_list.*

class RoomListFragment: Fragment(), MainView {

    private val TAG = RoomListFragment::class.simpleName
    private var mPresenter: MainPresenter? = null
    private lateinit var mAdapter: RoomsAdapter
    private lateinit var mPref: PreferencesUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mPresenter == null) {
            mPresenter = context?.let { MainPresenter(it) }
            mPresenter?.setView(this)
        }

        mPref = PreferencesUtil(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_room_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mPresenter?.getRooms(0,10)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        rcvRooms.apply {
            mAdapter = RoomsAdapter(mPref, onItemRoomClick = {
                userIds, name ->
                onItemClicked(userIds, name)
            })
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    override fun renderUserList(userList: ArrayList<UserProfileItem>?) {

    }

    override fun renderRoomList(roomList: ArrayList<RoomData>?) {
        if (roomList != null) {
            mAdapter.setData(roomList)
        }
    }

    override fun renderCreateRoom(createRoomData: CreateRoomData?, partnerName: String) {
        val id = createRoomData?.id
        val userIds = createRoomData?.userIds
        val name = createRoomData?.name
        startActivity(Intent(context, ChatActivity::class.java).apply {
            putExtra(RoomKey.ROOM_ID, id)
            putIntegerArrayListExtra(RoomKey.ROOM_USER_LIST, userIds)
            putExtra(RoomKey.ROOM_NAME, name)
        })
    }

    override fun renderRefreshToken(accessToken: String?) {

    }

    override fun onDestroy() {
        mPresenter?.removeView()
        super.onDestroy()
    }

    fun onItemClicked(userIds: List<Int>, name: String) {
        mPresenter?.createRoom(CreateRoomRequest(userIds, name), name)
    }

    companion object {
        @JvmStatic
        fun newInstance() = RoomListFragment()
    }
}