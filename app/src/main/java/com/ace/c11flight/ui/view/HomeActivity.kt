package com.ace.c11flight.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.ace.c11flight.R
import com.ace.c11flight.databinding.ActivityHomeBinding
import com.ace.c11flight.databinding.ActivityRegisterBinding
import com.ace.c11flight.ui.viewmodel.HomeActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isLoginInfoValid()
        setOnClickListeners()
    }

    private fun isLoginInfoValid() {
        viewModel.getLoginStatus().observe(this) {
            if (it) {
                binding.messageLogin.visibility = View.GONE
                Toast.makeText(this, "Login Verified", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setOnClickListeners() {

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}