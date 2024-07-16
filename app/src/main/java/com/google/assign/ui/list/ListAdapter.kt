package com.google.assign.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.assign.databinding.ItemLayoutBinding
import com.google.assign.model.User

class ListAdapter(private val adapterInterface: AdapterInterface) :
    PagingDataAdapter<User, ListAdapter.UserViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, adapterInterface)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class UserViewHolder(
        private val binding: ItemLayoutBinding,
        private val adapterInterface: AdapterInterface
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.apply {
                this.user = user
                pos.text = (absoluteAdapterPosition.plus(1)).toString()
                itemView.setOnClickListener {
                    adapterInterface.userClick(user)
                }
            }
        }

    }

    interface AdapterInterface {
        fun userClick(user: User)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                newItem == oldItem
        }
    }
}

