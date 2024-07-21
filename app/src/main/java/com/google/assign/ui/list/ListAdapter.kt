package com.google.assign.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.assign.databinding.ItemLayoutBinding
import com.google.assign.model.User
import com.google.assign.utils.diffUtil

class ListAdapter(private val adapterInterface: AdapterInterface) :
    PagingDataAdapter<User, ListAdapter.UserViewHolder>(diffUtil { old, new -> old.id == new.id }) {

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
        fun bind(item: User) {
            binding.apply {
                user = item
                tvPos.text = (absoluteAdapterPosition.plus(1)).toString()
                itemView.setOnClickListener {
                    adapterInterface.userClick(item)
                }
            }
        }
    }

    interface AdapterInterface {
        fun userClick(user: User)
    }
}

