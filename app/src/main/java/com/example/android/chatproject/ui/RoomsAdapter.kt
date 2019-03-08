package com.example.android.chatproject.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.chatproject.R
import com.example.android.chatproject.model.response.RoomData
import com.example.android.chatproject.util.PreferencesUtil
import kotlinx.android.synthetic.main.item_room.view.*

class RoomsAdapter(val mPref: PreferencesUtil, var onItemRoomClick: (listUserId: ArrayList<Int>, roomName: String) -> Unit): RecyclerView.Adapter<RoomsAdapter.RoomViewHolder>() {
    private var mDataRoom: MutableList<RoomData> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return RoomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDataRoom.size
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val roomData = mDataRoom[position]
        holder.mTvName.text = roomData.name

        holder.itemView.setOnClickListener {
            onItemRoomClick(roomData.userIds, roomData.name)
        }
    }

    fun setData(list: ArrayList<RoomData>) {
        mDataRoom.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        mDataRoom.clear()
        notifyDataSetChanged()
    }


    inner class RoomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var mTvName = itemView.tvName
        var mTvMessage = itemView.tvLatestMessage
    }

    fun ArrayList<Int>.checkNotMe(): String {
        var result = ""
        for (item in this) {
            if (item != mPref.userId)
                result = item.toString()
        }

        return result
    }

}