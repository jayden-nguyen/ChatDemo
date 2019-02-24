package com.example.android.chatproject.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.chatproject.R
import com.example.android.chatproject.model.RoomData
import kotlinx.android.synthetic.main.item_room.view.*

class RoomsAdapter: RecyclerView.Adapter<RoomsAdapter.RoomViewHolder>() {
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
        holder.mTvUserName.text = mDataRoom[position].messages[0].userName
        holder.mTvMessage.text = mDataRoom[position].messages[0].message

        holder.itemView.setOnClickListener {
            mListener?.onItemRoomClick(position)
        }
    }

    fun setData(list: List<RoomData>) {
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

}