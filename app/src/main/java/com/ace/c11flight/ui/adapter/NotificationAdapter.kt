package com.ace.c11flight.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ace.c11flight.data.model.NotificationData
import com.ace.c11flight.databinding.ItemNotificationBinding

class NotificationAdapter(
    private var items: ArrayList<NotificationData>,
    private var itemsFiltered: ArrayList<NotificationData>,
    private val onUserClick: (notif: NotificationData) -> Unit
) :
    RecyclerView.Adapter<NotificationAdapter.PostViewHolder>(), Filterable {
    fun setItems(list: List<NotificationData>?) {
        items = list as ArrayList<NotificationData>
        itemsFiltered = items
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindView(itemsFiltered[position])
    }

    override fun getItemCount(): Int = itemsFiltered.size


    inner class PostViewHolder(private val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(results: NotificationData) {
            itemView.setOnClickListener { }

            binding.tvMessage.text = results.message
            binding.tvDesc.text = "Rp. " + results.transaction?.total.toString()

            itemView.setOnClickListener { onUserClick.invoke(results) }

        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) itemsFiltered = items else {
                    val filteredList = ArrayList<NotificationData>()
                    items
                        .filter {
                            (it.user?.id.toString().contains(constraint!!))
                        }
                        .forEach { filteredList.add(it) }
                    itemsFiltered = filteredList
                }
                return FilterResults().apply { values = itemsFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemsFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<NotificationData>
                notifyDataSetChanged()
            }
        }
    }
}