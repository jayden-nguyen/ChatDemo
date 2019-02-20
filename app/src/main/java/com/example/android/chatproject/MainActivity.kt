package com.example.android.chatproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.android.chatproject.R.id.rcvRooms
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RoomsAdapter.OnItemRoomClick {

    private lateinit var mRoomsAdapter: RoomsAdapter
    private lateinit var mPref: PreferencesUtil
    private var isCalledApi = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRoomsAdapter = RoomsAdapter()
        mRoomsAdapter.setRoomListener(this)
        //
        mPref = PreferencesUtil(this)
        Toast.makeText(this, "AccessToken: ${mPref.accessToken}", Toast.LENGTH_SHORT).show()

        if (mPref.accessToken == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnLogout.setOnClickListener {
            mPref.userId = 0
            mPref.accessToken = null
            finish()
        }

        btnStartChat.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }
        getListRooms()
        setupRecyclerView()
    }

    //Call Api to get List Rooms
    private fun getListRooms() {

    }

    //render Rooms
    private fun renderRooms(list: List<RoomData>) {
        mRoomsAdapter.setData(list)
    }

    private fun setupRecyclerView() {
        rcvRooms.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mRoomsAdapter
        }
    }

    override fun onItemRoomClick(position: Int) {
        val intent = Intent(this, ChatActivity::class.java)
    }
}
