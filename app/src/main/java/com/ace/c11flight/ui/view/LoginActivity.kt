package com.ace.c11flight.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ace.c11flight.R
import com.ace.c11flight.data.local.user.AccountEntity
import com.ace.c11flight.databinding.ActivityLoginBinding
import com.ace.c11flight.ui.viewmodel.LoginActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isLoginInfoValid()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnLogin.setOnClickListener{ checkLogin() }
        binding.tvGotoRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun checkLogin() {
        if (validateInput()) {
            val username = binding.etUsername.text.toString()
            viewModel.getUser(username)

            viewModel.getUser.observe(this) {
                checkAccount(it)
            }
        }
    }

    private fun validateInput(): Boolean {
        var isValid = true
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        if (username.isEmpty()) {
            isValid = false
            binding.etUsername.error = getString(R.string.username_is_empty)
        }
        if (password.isEmpty()) {
            isValid = false
            binding.etPassword.error = getString(R.string.password_is_empty)
        }
        return isValid
    }

    fun checkAccount(account: AccountEntity?) {
        account?.let {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            val loginStatus = username == account.username && password == account.password
            if (loginStatus) {
//                goToHome()
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.username_or_password_incorrect),
                    Toast.LENGTH_SHORT
                ).show()
            }
            saveLoginInfo(
                account.username,
                account.email,
                account.password,
                account.accountId,
                loginStatus
            )
        }
    }

    fun saveLoginInfo(
        username: String,
        email: String,
        password: String,
        accountId: Long,
        loginStatus: Boolean
    ) {
        viewModel.setAccount(username, email, password, accountId)
        viewModel.saveLoginStatus(loginStatus)
    }

    private fun isLoginInfoValid() {
        viewModel.getLoginStatus().observe(this) {
            if (it) {
                goToHome()
                Toast.makeText(this, "Login Verified", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}