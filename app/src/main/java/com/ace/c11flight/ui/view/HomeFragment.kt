package com.ace.c11flight.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ace.c11flight.databinding.FragmentHomeBinding
import com.ace.c11flight.ui.viewmodel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeFragmentViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isLoginInfoValid()
        setOnClickListeners()
        setUsername()
    }

    private fun setOnClickListeners() {

    }
    private fun setUsername() {
        viewModel.getAccountPrefs().observe(viewLifecycleOwner){
            binding.tvUsername.text = it.username
        }
    }

    private fun isLoginInfoValid() {
        viewModel.getLoginStatus().observe(viewLifecycleOwner) {
            if (it) {
                binding.hi.visibility = View.VISIBLE
                binding.tvUsername.visibility = View.VISIBLE
            }
        }
    }

}