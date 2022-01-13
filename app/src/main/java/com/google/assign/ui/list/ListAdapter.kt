package com.google.assign.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.assign.databinding.ItemLayoutBinding
import com.google.assign.model.User

class ListAdapter(private val userInterface: UserInterface) :
    PagingDataAdapter<User, ListAdapter.UserViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, userInterface)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class UserViewHolder(
        private val binding: ItemLayoutBinding,
        private val userInterface: UserInterface
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.apply {
                this.user = user
                pos.text = (absoluteAdapterPosition + 1).toString()
            }

            binding.item.setOnClickListener {
                userInterface.userClick(user)
            }
        }

    }

}


interface UserInterface {
    fun userClick(user: User)
}


val DiffUtil = object : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem.firstName == newItem.firstName

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
        newItem == oldItem
}
