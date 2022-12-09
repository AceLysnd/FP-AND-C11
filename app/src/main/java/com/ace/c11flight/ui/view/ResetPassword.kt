package com.ace.c11flight.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.ace.c11flight.R
import com.ace.c11flight.databinding.ActivityResetPasswordBinding

class ResetPassword : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}