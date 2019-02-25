package com.example.android.chatproject.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.android.chatproject.model.response.UserProfileItem

class UserListAdapter(val onClick: () -> Unit): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    private val mUserList = ArrayList<UserProfileItem>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

    }

    override fun getItemCount(): Int {

    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }
}