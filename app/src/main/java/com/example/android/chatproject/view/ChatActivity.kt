package com.example.android.chatproject.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.TextView
import com.example.android.chatproject.util.PreferencesUtil
import com.example.android.chatproject.R
import com.example.android.chatproject.model.Message
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_chat.*
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.client.StompClient

class ChatActivity: AppCompatActivity() {
    private lateinit var mChatAdapter: ChatAdapter
    private lateinit var mStompClient: StompClient
    private val TAG = ChatActivity::class.simpleName
    private val mCompositeDisposable = CompositeDisposable()
    private lateinit var mPref: PreferencesUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        mChatAdapter = ChatAdapter(this)
        setUpRecyclerView()
        mPref = PreferencesUtil(this)
        setupStompClient()

        imgBack.setOnClickListener {
            finish()
        }

        btnSend.setOnClickListener {
            val jsonObj = JSONObject()
            jsonObj.put("message", edtMessageContent.text.toString())
            mStompClient.send("/topic/greetings",edtMessageContent.text.toString()).subscribe()
            edtMessageContent.setText("", TextView.BufferType.EDITABLE)
        }
    }

    @SuppressLint("CheckResult")
    private fun setupStompClient() {
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://flinkgo.com:8081/websocket?auth=${mPref.accessToken}")
        mStompClient.withClientHeartbeat(10000).withServerHeartbeat(10000)
        mStompClient.connect()
            mStompClient.topic("/topic/greetings").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    mChatAdapter.addItem(Message(0, "Me", it.payload))
                    rcvChat.smoothScrollToPosition(mChatAdapter.itemCount)
            },{
                Log.d("testStompMessage","onERROR ${it.message}")
            })
    }

    private fun setUpRecyclerView() {
        rcvChat.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mChatAdapter
            smoothScrollToPosition(mChatAdapter.itemCount)
        }
    }

    override fun onDestroy() {
        mCompositeDisposable.dispose()
        super.onDestroy()
    }
}