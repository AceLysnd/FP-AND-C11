package com.ace.c11flight.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ace.c11flight.databinding.ActivityLoginBinding
import com.ace.c11flight.databinding.ActivityRegisterBinding
import com.ace.c11flight.ui.viewmodel.LoginActivityViewModel
import com.ace.c11flight.utils.viewModelFactory

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.tvGotoRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}