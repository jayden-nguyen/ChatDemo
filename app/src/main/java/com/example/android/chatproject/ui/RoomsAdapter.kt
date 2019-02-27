package com.example.android.chatproject.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.chatproject.R
import com.example.android.chatproject.model.response.RoomData
import com.example.android.chatproject.util.PreferencesUtil
import kotlinx.android.synthetic.main.item_room.view.*

class RoomsAdapter(val mPref: PreferencesUtil): RecyclerView.Adapter<RoomsAdapter.RoomViewHolder>() {
    private var mDataRoom: MutableList<RoomData> = mutableListOf()
    private var mListener: OnItemRoomClick? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return RoomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDataRoom.size
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.mTvUserName.text = mDataRoom[position].userIds.checkNotMe()

        holder.itemView.setOnClickListener {
            mListener?.onItemRoomClick(position)
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

    fun setRoomListener(listener: OnItemRoomClick) {
        mListener = listener
    }

    inner class RoomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var mTvUserName = itemView.tvUserName
        var mTvMessage = itemView.tvLatestMessage
    }

    interface OnItemRoomClick{
        fun onItemRoomClick(position: Int)
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