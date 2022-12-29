package com.ace.c11flight.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ace.c11flight.data.model.Ticket
import com.ace.c11flight.databinding.ItemTicketBinding
import com.ace.c11flight.ui.view.BookingActivity.Companion.AIRPORT_CITY_FROM
import com.ace.c11flight.ui.view.BookingActivity.Companion.AIRPORT_CODE_FROM
import com.ace.c11flight.ui.view.BookingActivity.Companion.AIRPORT_CODE_TO
import com.ace.c11flight.ui.view.BookingActivity.Companion.CATEGORY_CODE
import android.widget.Filter

class TicketAdapter(
    private var items: ArrayList<Ticket>,
    private var itemsFiltered: ArrayList<Ticket>,
    private val onUserClick: (ticket: Ticket) -> Unit
) :
    RecyclerView.Adapter<TicketAdapter.PostViewHolder>(), Filterable {


    fun setItems(list: List<Ticket>?) {
        items = list as ArrayList<Ticket>
        itemsFiltered = items
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindView(itemsFiltered[position])
    }

    override fun getItemCount(): Int = itemsFiltered.size


    inner class PostViewHolder(private val binding: ItemTicketBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(results: Ticket) {
            itemView.setOnClickListener { }
            binding.tvPlaneDesc.text = results.flight?.plane?.name
            binding.tvType.text = results.type.toString()
            binding.tvPrice.text = results.price.toString()
            binding.tvDesc.text = results.desc.toString()
            binding.tvToCode.text = results.flight?.to?.cityCode
            binding.tvToDesc.text = results.flight?.to?.city
            binding.tvFromCode.text = results.flight?.from?.cityCode
            binding.tvFromDesc.text = results.flight?.from?.city

            itemView.setOnClickListener { onUserClick.invoke(results) }

        }
    }

    companion object {
        var PASSED_FILTER = 0
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) itemsFiltered = items else {
                    val filteredList = ArrayList<Ticket>()
                    items
                        .filter {
                            (it.flight?.from?.cityCode?.contains(constraint!!))!! &&
                                    (it.flight?.to?.cityCode?.contains(AIRPORT_CODE_TO)!!)
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
                    results.values as ArrayList<Ticket>
                notifyDataSetChanged()
            }
        }
    }

    fun getSecondFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) itemsFiltered = items else {
                    val filteredList = ArrayList<Ticket>()
                    items
                        .filter {
                            (it.flight?.to?.cityCode?.contains(constraint!!)!!)
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
                    results.values as ArrayList<Ticket>
                notifyDataSetChanged()
            }
        }
    }

    fun getThirdFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) itemsFiltered = items else {
                    val filteredList = ArrayList<Ticket>()
                    items
                        .filter {
                            (it.type?.contains(constraint!!)!!)
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
                    results.values as ArrayList<Ticket>
                notifyDataSetChanged()
            }
        }
    }
}