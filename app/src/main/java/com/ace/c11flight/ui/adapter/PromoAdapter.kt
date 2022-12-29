package com.ace.c11flight.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ace.c11flight.data.model.Promo
import com.ace.c11flight.databinding.ItemPromoBinding
import com.bumptech.glide.Glide

class PromoAdapter(
    private var items: MutableList<Promo>,
    private val onAirportClick: (promo: Promo) -> Unit
) :
    RecyclerView.Adapter<PromoAdapter.PostViewHolder>() {

    fun setItems(items: List<Promo>?) {
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
        val binding = ItemPromoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size


    inner class PostViewHolder(private val binding: ItemPromoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Promo) {
            itemView.setOnClickListener { }
            binding.tvPromoName.text = item.name.toString()
            binding.amountDiscount.text = item.discount.toString()
            binding.tvDesc.text = item.description

            Glide.with(binding.promoPlaceholder.context).load(item.photo).into(binding.promoPlaceholder)

            itemView.setOnClickListener { onAirportClick.invoke(item) }

        }
    }
}