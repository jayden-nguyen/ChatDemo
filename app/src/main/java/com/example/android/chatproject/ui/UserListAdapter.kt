package com.example.android.chatproject.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.chatproject.R
import com.example.android.chatproject.model.response.UserProfileItem
import com.example.android.chatproject.util.convertToString
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.*
import kotlin.collections.ArrayList

class UserListAdapter(val onClick: () -> Unit): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    private val mUserList = ArrayList<UserProfileItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mUserList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mUserList[position])
    }

    fun addUserList(list: ArrayList<UserProfileItem>) {
        mUserList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        mUserList.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var mTvUserName = view.tvUserName
        var mTvGender = view.tvGender
        var mTvBirthday = view.tvBirthday

        fun bind(userProfileItem: UserProfileItem) {
            mTvUserName.text = userProfileItem.userName
            mTvGender.text = userProfileItem.userInfo.gender
            mTvBirthday.text = Date(userProfileItem.userInfo.dob).convertToString()
            itemView.setOnClickListener {
                onClick()
            }
        }
    }
}