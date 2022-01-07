package com.google.assign.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.assign.R
import com.google.assign.databinding.ItemLayoutBinding
import com.google.assign.model.User
import com.google.assign.utils.toast
import com.google.assign.view.ListFragmentDirections

class ListAdapter(var ctx: Context) : PagingDataAdapter<User, ListAdapter.UserViewHolder>(USER_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return UserViewHolder(view)
    }

    //Data binding not used due to time constrained
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        user?.let {
            with(holder) {
                binding.textFirstName.text = user.first_name
                binding.textLastName.text = user.last_name
                binding.textPhone.text = user.phone_number
                binding.textCity.text = user.address.city
                Glide.with(ctx).load(user.avatar).into(binding.avatar)
            }
        }


        holder.itemView.setOnClickListener {
            ctx.toast("$position clicked")

            val action = ListFragmentDirections.actionListFragmentToDetailFragment()
            action.data = user
            Navigation.findNavController(it).navigate(action)
        }
    }


    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemLayoutBinding.bind(view)
    }


    // Checking the ids for old and new item
    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.first_name == newItem.first_name

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                newItem == oldItem
        }
    }


}