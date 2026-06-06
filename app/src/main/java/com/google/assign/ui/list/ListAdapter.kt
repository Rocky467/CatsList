package com.google.assign.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.assign.databinding.ItemLayoutBinding
import com.google.assign.model.Cats
import com.google.assign.utils.Util.diffUtil

class ListAdapter(private val adapterInterface: AdapterInterface) :
    PagingDataAdapter<Cats, ListAdapter.UserViewHolder>(diffUtil { old, new -> old.id == new.id }) {

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
        fun bind(item: Cats) {
            binding.apply {
                cats = item
                tvPos.text = (absoluteAdapterPosition.plus(1)).toString()
                itemView.setOnClickListener {
                    adapterInterface.onClick(item)
                }
            }
        }
    }

    interface AdapterInterface {
        fun onClick(cat: Cats)
    }
}

