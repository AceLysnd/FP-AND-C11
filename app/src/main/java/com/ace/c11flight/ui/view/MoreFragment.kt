package com.ace.c11flight.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ace.c11flight.R
import com.ace.c11flight.databinding.FragmentHomeBinding
import com.ace.c11flight.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnHistory.setOnClickListener{
            activity?.let {
                val intent = Intent(it, TransactionHistoryActivity::class.java)
                it.startActivity(intent)
            }
        }
        binding.btnWishlist.setOnClickListener{
            activity?.let {
                val intent = Intent(it, WishlistActivity::class.java)
                it.startActivity(intent)
            }
        }
    }
}