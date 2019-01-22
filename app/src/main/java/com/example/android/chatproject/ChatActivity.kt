package com.example.android.chatproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity: AppCompatActivity() {
    private lateinit var mChatAdapter: ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        mChatAdapter = ChatAdapter(this)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        rcvChat.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mChatAdapter
        }
    }
}