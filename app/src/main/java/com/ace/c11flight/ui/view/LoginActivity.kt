package com.ace.c11flight.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ace.c11flight.R
import com.ace.c11flight.data.model.LoginInfo
import com.ace.c11flight.data.services.ApiHelper
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

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnLogin.setOnClickListener { checkLogin() }
        binding.tvGotoRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun checkLogin() {
        val apiService = ApiHelper()
        if (validateInput()) {

            val loginInfo = LoginInfo(
                status = "",
                id = null,
                username = "",
                email = binding.etUsername.text.toString(),
                password = binding.etPassword.text.toString(),
                data = null,
                token = ""
            )

            apiService.loginUser(loginInfo) {
                if (it?.status == "OK") {
                    Toast.makeText(this, "login OK", Toast.LENGTH_SHORT).show()
                    saveLoginStatus(
                        loginStatus = true
                    )
                    saveLoginInfo(
                        username = it.data?.username!!,
                        email = it.data.email!!,
                        password = it.data.password!!,
                        id = it.data.id!!,
                        token = it.token.toString()
                    )
                    goToHome()
                } else {
                    Toast.makeText(this, "Email or password is not identified", Toast.LENGTH_SHORT)
                        .show()
                }
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

    fun saveLoginInfo(
        username: String,
        email: String,
        password: String,
        id: Long,
        token: String
    ) {
        viewModel.setAccount(username, email, password, id, token)
    }

    fun saveLoginStatus(
        loginStatus: Boolean
    ) {
        viewModel.saveLoginStatus(loginStatus)
    }

    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private var backButtonCount = 0
    override fun onBackPressed() {
        if (backButtonCount < 1) {
            Toast.makeText(this, getString(R.string.press_back_again), Toast.LENGTH_SHORT).show()
            backButtonCount += 1
        } else {
            moveTaskToBack(true)
            backButtonCount = 0
        }
    }

}