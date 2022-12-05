package com.ace.c11flight.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ace.c11flight.R
import com.ace.c11flight.data.local.user.AccountEntity
import com.ace.c11flight.data.model.UserInfo
import com.ace.c11flight.data.services.ApiHelper
import com.ace.c11flight.databinding.ActivityRegisterBinding
import com.ace.c11flight.ui.viewmodel.RegisterActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.tvGotoLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnCreateAccount.setOnClickListener {
            createAccount()
        }
    }

    private fun createAccount() {
        val apiService = ApiHelper()
        if (validateInput()) {
            val user = AccountEntity(
                username = binding.etUsername.text.toString(),
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )
            val userInfo = UserInfo(
                status = null,
                id = null,
                username = binding.etUsername.text.toString(),
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )
            apiService.registerUser(userInfo) {
                if (it?.status == "OK") {
                    viewModel.registerUser(user)
                    goToLogin()
                    Toast.makeText(this, getString(R.string.account_created), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "error register user", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun validateInput(): Boolean {
        var isValid = true
        val username = binding.etUsername.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()

        if (username.isEmpty()) {
            isValid = false
            binding.etUsername.error = getString(R.string.username_is_empty)
        }
        if (email.isEmpty()) {
            isValid = false
            binding.etEmail.error = getString(R.string.email_is_empty)
        }
        if (password.isEmpty()) {
            isValid = false
            binding.etPassword.error = getString(R.string.password_is_empty)
        }
        if (confirmPassword.isEmpty()) {
            isValid = false
            binding.etConfirmPassword.error = getString(R.string.confirm_password)
        }
        if (password != confirmPassword) {
            isValid = false
            Toast.makeText(this, getString(R.string.password_mismatch), Toast.LENGTH_SHORT).show()
        }
        return isValid
    }
}