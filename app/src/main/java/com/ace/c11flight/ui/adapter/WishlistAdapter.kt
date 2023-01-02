package com.ace.c11flight.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ace.c11flight.data.local.wishlist.WishlistEntity
import com.ace.c11flight.databinding.ItemWishlistBinding

class WishlistAdapter(
    private var items: MutableList<WishlistEntity>,
    private val listener: WishlistItemListener
) :
    RecyclerView.Adapter<WishlistAdapter.PostViewHolder>() {


    fun setItems(items: List<WishlistEntity>?) {
        this.items.clear()
        if (items != null) {
            this.items.addAll(items)
        }
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemWishlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size


    inner class PostViewHolder(private val binding: ItemWishlistBinding, private val listener: WishlistItemListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: WishlistEntity) {
            itemView.setOnClickListener { }
            binding.tvPriceDesc.text = item.price
            binding.tvToCode.text = item.toCode
            binding.tvToDesc.text = item.toCity
            binding.tvFromCode.text = item.fromCode
            binding.tvFromDesc.text = item.fromCity

            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }
            binding.ivDelete.setOnClickListener{
                listener.onDeleteMenuClicked(item)
            }
        }
    }
}

interface WishlistItemListener {
    fun onItemClicked(item: WishlistEntity)
    fun onDeleteMenuClicked(item: WishlistEntity)
}