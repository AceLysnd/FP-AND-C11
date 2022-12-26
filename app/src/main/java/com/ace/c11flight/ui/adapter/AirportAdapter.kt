package com.ace.c11flightadmin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ace.c11flight.data.model.Airport
import com.ace.c11flight.databinding.ItemListAirportBinding

class AirportAdapter(
    private var items: MutableList<Airport>,
    private val onAirportClick: (airport: Airport) -> Unit
) :
    RecyclerView.Adapter<AirportAdapter.PostViewHolder>() {


    fun setItems(items: List<Airport>?) {
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
        val binding = ItemListAirportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size


    inner class PostViewHolder(private val binding: ItemListAirportBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Airport) {
            itemView.setOnClickListener { }
            binding.tvAirportCity.text = item.city.toString()
            binding.tvAirportCode.text = item.cityCode.toString()
            binding.tvAirportCountry.text = item.country.toString()
            binding.tvAirportName.text = item.name.toString()

            itemView.setOnClickListener { onAirportClick.invoke(item) }

        }
    }
}