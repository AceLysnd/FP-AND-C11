package com.ace.c11flight.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.ace.c11flight.data.model.Ticket
import com.ace.c11flight.data.model.TransactionData
import com.ace.c11flight.databinding.ItemTransactionsBinding
import com.ace.c11flight.ui.view.HomeActivity.Companion.USERNAME

class TransactionHistoryAdapter (
    private var items: ArrayList<TransactionData>,
    private var itemsFiltered: ArrayList<TransactionData>,
    private val onUserClick: (transactionData: TransactionData) -> Unit
) :
    RecyclerView.Adapter<TransactionHistoryAdapter.PostViewHolder>(), Filterable {


    fun addItem(list: List<TransactionData>?) {
        items = list as ArrayList<TransactionData>
        itemsFiltered = items
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemTransactionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindView(itemsFiltered[position])
    }

    override fun getItemCount(): Int = itemsFiltered.size


    inner class PostViewHolder(private val binding: ItemTransactionsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(results: TransactionData) {
            itemView.setOnClickListener { }
            binding.tvUsernameDesc.text = results.user?.username
            binding.tvType.text = results.ticket?.type.toString()
            binding.tvPrice.text = "Rp. " + results.total.toString()
            binding.tvPromo.text = results.promo?.name
            binding.tvToCode.text = results.ticket?.flight?.to?.cityCode
            binding.tvToDesc.text = results.ticket?.flight?.to?.city
            binding.tvFromCode.text = results.ticket?.flight?.from?.cityCode
            binding.tvFromDesc.text = results.ticket?.flight?.from?.city
            binding.tvTransactionTime.text = results.createdAt
            binding.tvTransId.text = "#" + results.id.toString()

            itemView.setOnClickListener { onUserClick.invoke(results) }

        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) itemsFiltered = items else {
                    val filteredList = ArrayList<TransactionData>()
                    items
                        .filter {
                            (it.userId.toString().contains(constraint!!))
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
                    results.values as ArrayList<TransactionData>
                notifyDataSetChanged()
            }
        }
    }
}