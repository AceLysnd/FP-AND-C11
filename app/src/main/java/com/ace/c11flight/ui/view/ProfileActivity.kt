package com.ace.c11flight.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

        setUserId()
        setUserInfo()
        setOnclickListeners()
    }

    private fun setUserId() {
        viewModel.getAccountPrefs().observe(this){
            ACCOUNT_ID = it.accountId
        }
    }

    private fun setUserInfo() {
        viewModel.getUserById()
        viewModel._accountData.observe(this){
            binding.tvUsername.text = it.data?.username
            binding.tvEmail.text = it.data?.email
            binding.tvFirstName.text = it.data?.firstName.toString()
            binding.tvLastName.text = it.data?.lastName.toString()
            binding.tvAddress.text = it.data?.address.toString()
            binding.tvPhone.text = it.data?.id.toString()
        }

    }

    private fun setOnclickListeners() {
        binding.btnLogOut.setOnClickListener{
            viewModel.saveLoginStatus(false)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    companion object {
        var ACCOUNT_ID: Long = 0
    }
}