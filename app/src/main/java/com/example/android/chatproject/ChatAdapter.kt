package com.example.android.chatproject

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_my_chat.view.*
import kotlinx.android.synthetic.main.item_other_chat.view.*

class ChatAdapter(context: Context): RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    private val mChatDataList: MutableList<Message> = mutableListOf()
    private val mPref = PreferencesUtil(context)
    private var viewType: List<Int> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType) {
            VIEW_TYPE_MY_CHAT -> MyChatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_my_chat, parent, false))
            VIEW_TYPE_OTHER_CHAT -> OtherChatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_other_chat, parent, false))
            else -> throw IllegalStateException("unknown Type $viewType")
        }
    }

    override fun getItemCount(): Int = mChatDataList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mChatDataList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return viewType[position]
    }

    abstract class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        abstract fun bind(model : Any)
    }

    fun addItem(item: Message) {
        mChatDataList.add(item)
        viewType += mapToViewType(item)
        notifyItemInserted(mChatDataList.size - 1)
    }

    fun addItems(list: List<Message>) {
        mChatDataList.addAll(list)
        viewType += list.map { mapToViewType(it) }
        notifyItemRangeInserted(0, list.size)
    }

    fun setItems(list: List<Message>) {
        mChatDataList.addAll(list)
        viewType += list.map { mapToViewType(it) }
        notifyDataSetChanged()
    }

    private fun mapToViewType(item: Message): Int {
        return if (item.userId == mPref.getUserId()) {
            VIEW_TYPE_MY_CHAT
        } else
            VIEW_TYPE_OTHER_CHAT
    }

    inner class MyChatViewHolder(itemView: View): ViewHolder(itemView) {
        private var mTvUserName = itemView.findViewById<TextView>(R.id.tvUserName)
        private var mTvChatItem = itemView.findViewById<TextView>(R.id.tvChatItem)

        override fun bind(model: Any) {
            (model as Message).apply {
                mTvUserName.text = "Me"
                mTvChatItem.text = model.message
            }
        }
    }

    inner class OtherChatViewHolder(itemView: View): ViewHolder(itemView) {
        private var mTvUserName = itemView.findViewById<TextView>(R.id.tvUserName)
        private var mTvChatItem = itemView.findViewById<TextView>(R.id.tvChatItem)

        override fun bind(model: Any) {
            (model as Message).apply {
                mTvUserName.text = userName
                mTvChatItem.text = message
            }
        }
    }

    companion object {
        val TAG = ChatAdapter::class.simpleName
        val VIEW_TYPE_MY_CHAT = 0
        val VIEW_TYPE_OTHER_CHAT = 1
    }
}