package com.ace.c11flight.data.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ace.c11flight.R
//
//class AdapterWislit() :RecyclerView.Adapter<AdapterWislit.ViewHolder>() {
//
////
////    class ViewHolder(itemView :View) :RecyclerView.ViewHolder(itemView) {
////        val code = itemView.findViewById<TextView>(R.id.tv_code)
////        val from = itemView.findViewById<TextView>(R.id.et_to_penerbangan)
////        val passenger = itemView.findViewById<TextView>(R.id.Number_passenger)
////    }
////
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
////        val view = LayoutInflater.from(parent.context)
////            .inflate(R.layout.list_airport,parent,false)
////        return ViewHolder(view)
////    }
////
////    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
////        holder.code.text = wislist[position].code
////        holder.from.text = wislist[position].from
////        holder.passenger.text = wislist[position].passenger.toString()
////
////    }
////
////    override fun getItemCount(): Int {
////        return wislist.size
////    }
//}