package com.example.android.chatproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RoomsAdapter.OnItemRoomClick {

    private lateinit var mRoomsAdapter: RoomsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRoomsAdapter = RoomsAdapter()
        mRoomsAdapter.setRoomListener(this)
        //Call API
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