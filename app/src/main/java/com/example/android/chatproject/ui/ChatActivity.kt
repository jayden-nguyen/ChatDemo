package com.example.android.chatproject.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.TextView
import com.example.android.chatproject.util.PreferencesUtil
import com.example.android.chatproject.R
import com.example.android.chatproject.model.Message
import com.example.android.chatproject.util.RoomKey.ROOM_ID
import com.example.android.chatproject.util.RoomKey.ROOM_NAME
import com.example.android.chatproject.util.RoomKey.ROOM_PARTNER_NAME
import com.example.android.chatproject.util.RoomKey.ROOM_USER_LIST
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
    private  var roomId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        mChatAdapter = ChatAdapter(this)
        setUpRecyclerView()
        mPref = PreferencesUtil(this)
        roomId = intent.getIntExtra(ROOM_ID, 0)
        val userIds = intent.getIntegerArrayListExtra(ROOM_USER_LIST)
        var friendId = 0
        for (i in userIds) {
            if (i != mPref.userId)
                friendId = i
        }
        val friendName = intent.getStringExtra(ROOM_PARTNER_NAME)
        setupStompClient()

        imgBack.setOnClickListener {
            finish()
        }

        btnSend.setOnClickListener {
            val jsonObj = JSONObject()
            jsonObj.put("message", edtMessageContent.text.toString())
            jsonObj.put("toUserId", friendId)
            jsonObj.put("toUserName", friendName)
            mStompClient.send("/chat/private", jsonObj.toString()).subscribe()
            edtMessageContent.setText("", TextView.BufferType.EDITABLE)
        }
    }

    @SuppressLint("CheckResult")
    private fun setupStompClient() {
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://flinkgo.com:8081/websocket?auth=${mPref.accessToken}")
        mStompClient.withClientHeartbeat(10000).withServerHeartbeat(10000)
        mStompClient.connect()
            mStompClient.topic("/user/queue/reply").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    try {
                        val data = JSONObject(it.payload)
                        val userId = data.getInt("fromUserId")
                        val message = data.getString("message")
                        val userName = data.getString("fromUserName")
                        Log.d(TAG, "PayLoad is ${it.payload}")
                        mChatAdapter.addItem(Message(userId, userName, message))
                        rcvChat.smoothScrollToPosition(mChatAdapter.itemCount)
                    } catch (e: Throwable) {
                        Log.e(TAG, "${e.message}")
                    }
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