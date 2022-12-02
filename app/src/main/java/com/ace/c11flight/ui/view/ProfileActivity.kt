package com.ace.c11flight.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ace.c11flight.databinding.ActivityProfileBinding
import com.ace.c11flight.ui.viewmodel.ProfileActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private var _binding: ActivityProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnclickListeners()
    }

    private fun setOnclickListeners() {
        binding.btnLogOut.setOnClickListener{
            viewModel.saveLoginStatus(false)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}