package com.ace.c11flight.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.ace.c11flight.R
import com.ace.c11flight.databinding.ActivityEditProfileBinding
import com.ace.c11flight.ui.viewmodel.EditProfileViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class EditProfileActivity : AppCompatActivity() {
    private var _binding: ActivityEditProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EditProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUserInfo()
        setOnclickListeners()
    }

    private fun setUserInfo() {
        viewModel.getUserData()
        viewModel.loadingState.observe(this) { isLoading ->
            binding.pbPost.isVisible = isLoading
        }

        viewModel.errorState.observe(this) { errorData ->
            binding.tvError.isVisible = errorData.first
            errorData.second?.message?.let {
                binding.tvError.text = it
            }
        }
        viewModel._dataResult.observe(this) {
            binding.etUsername.setText(it.data?.username)
            binding.etEmail.setText(it.data?.email)
            binding.etFirstname.setText(it.data?.firstName.toString())
            binding.etLastname.setText(it.data?.lastName.toString())
            binding.etAddress.setText(it.data?.address.toString())
            binding.etPhone.setText(it.data?.phone.toString())
        }

    }

    private fun setOnclickListeners() {
        binding.btnSave.setOnClickListener {
            createDialog()
        }
    }

    private fun saveForm() {



    }

    private fun createDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit Profile")
        builder.setMessage("Save edit?")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->

            val jsonObject = JSONObject()
            jsonObject.put("username",binding.etUsername.text)
            jsonObject.put("email",binding.etEmail.text)
            jsonObject.put("firstName",binding.etFirstname.text)
            jsonObject.put("lastName",binding.etLastname.text)
            jsonObject.put("address",binding.etAddress.text)
            jsonObject.put("phone",binding.etPhone.text)

            val jsonObjectString = jsonObject.toString()

            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

            viewModel.updateUser(requestBody)
            viewModel.loadingState.observe(this) { isLoading ->
                binding.pbPost.isVisible = isLoading
            }

            viewModel.errorState.observe(this) { errorData ->
                binding.tvError.isVisible = errorData.first
                errorData.second?.message?.let {
                    binding.tvError.text = it
                }
            }
            Toast.makeText(
                applicationContext,
                "Edit Success!", Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->

        }
        builder.show()
    }

    companion object {
        var PHOTO_URL: String? = ""
    }
}