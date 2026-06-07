package com.google.assign.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.assign.databinding.ItemLayoutBinding
import com.google.assign.model.Cats
import com.google.assign.utils.Util.diffUtil

class ListAdapter(private val adapterInterface: AdapterInterface) :
    PagingDataAdapter<Cats, ListAdapter.ItemViewHolder>(diffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, adapterInterface)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ItemViewHolder(
        private val binding: ItemLayoutBinding,
        private val adapterInterface: AdapterInterface
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Cats) {
            binding.apply {
                cats = item
                tvPos.text = (absoluteAdapterPosition.plus(1)).toString()
                itemView.setOnClickListener {
                    adapterInterface.itemClick(item)
                }
            }
        }
    }

    interface AdapterInterface {
        fun itemClick(cat: Cats)
    }
}

