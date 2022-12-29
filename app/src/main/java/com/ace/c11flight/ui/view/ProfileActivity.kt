package com.ace.c11flight.ui.view

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.ace.c11flight.databinding.ActivityProfileBinding
import com.ace.c11flight.ui.viewmodel.ProfileActivityViewModel
import com.ace.c11flight.ui.viewmodel.UpdatePhotoProfile
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    //image
    private var imageMultiPart: MultipartBody.Part? = null
    private var imageUri: Uri? = Uri.EMPTY
    private var imageFile: File? = null
    private lateinit var sharedPref: SharedPreferences


    private var _binding: ActivityProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = this.getSharedPreferences("datatoken", Context.MODE_PRIVATE)
        profileData()
        setOnclickListeners()

        binding.btnUpdateImage.setOnClickListener {
            checkingPermissions()
        }
    }

    private fun setOnclickListeners() {
        binding.btnLogOut.setOnClickListener{
            viewModel.saveLoginStatus(false)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnEditProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }
    companion object {
        var ACCOUNT_ID: Long = 0
    }

    private fun postProfileImage(id : Int, imageMultiPart: MultipartBody.Part) {
        val viewModel = ViewModelProvider(this)[UpdatePhotoProfile::class.java]
        viewModel.putProfileImageData().observe(this) {
            if (it != null){
                Toast.makeText(this, "Profile Picture Successfully Changed !", Toast.LENGTH_SHORT).show()
                getProfileImage(sharedPref.getString("token","").toString())
            }
        }
        viewModel.putProfileImage(sharedPref.getString("token","").toString(),id, imageMultiPart)
    }

    private fun profileData() {
        val viewModel = ViewModelProvider(this)[UpdatePhotoProfile::class.java]
        viewModel.callProfileApi(sharedPref.getString("token","").toString())
        viewModel.getProfileData().observe(this) {
            if (it != null) {
                Glide
                    .with(this)
                    .load(it.data!!.photo)
                    .centerCrop()
                    .into(binding.imgProfile)
                binding.tvUsername.text = it.data.username
                binding.tvEmail.text = it.data.email.toString()
                binding.tvFirstName.text = it.data.firstName.toString()
                binding.tvLastName.text = it.data.lastName.toString()
                binding.tvPhone.text = it.data.phone.toString()
                binding.tvAddress.text = it.data.address.toString()


            } else {
                Toast.makeText(this, "Failed to read profile data", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun getProfileImage(token : String){
        val viewModel = ViewModelProvider(this)[UpdatePhotoProfile::class.java]
        viewModel.callProfileApi(token)
        viewModel.getProfileData().observe(this) {
            if (it.data!!.photo != "") {
                Glide
                    .with(this)
                    .load(it.data.photo)
                    .centerCrop()
                    .into(binding.imgProfile)
            } else {
                Toast.makeText(this, "No Profile Image Found", Toast.LENGTH_SHORT).show()
                Log.d("Profile Image Res", it.toString())
            }
        }
    }

    private fun checkingPermissions() {
        if (isGranted(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                97,
            )
        ) {
            openGallery()
        }
    }

    private fun isGranted(
        activity: Activity,
        permission: String,
        permissions: Array<String>,
        request: Int,
    ): Boolean {
        val permissionCheck = ActivityCompat.checkSelfPermission(activity, permission)
        return if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showPermissionDeniedDialog()
            } else {
                ActivityCompat.requestPermissions(activity, permissions, request)
            }
            false
        } else {
            true
        }
    }


    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("Permission Denied")
            .setMessage("Permission is denied, Please allow permissions from App Settings.")
            .setPositiveButton(
                "App Settings"
            ) { _, _ ->
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", this.packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .show()
    }

    private fun openGallery() {
        getContent.launch("image/*")
    }


    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val contentResolver: ContentResolver = this.contentResolver
                val type = contentResolver.getType(it)
                imageUri = it

                val fileNameimg = "${System.currentTimeMillis()}.png"

                val tempFile = File.createTempFile("payment", fileNameimg, null)
                imageFile = tempFile
                val inputstream = contentResolver.openInputStream(uri)
                tempFile.outputStream().use    { result ->
                    inputstream?.copyTo(result)
                }
                val requestBody: RequestBody = tempFile.asRequestBody(type?.toMediaType())
                imageMultiPart = MultipartBody.Part.createFormData("file", tempFile.name, requestBody)

                postProfileImage(sharedPref.getLong("id",0).toInt(), imageMultiPart!!)
            }
        }


}




//    private fun setUserId() {
//        viewModel.getAccountPrefs().observe(this){
//            ACCOUNT_ID = it.accountId
//        }
//    }
//
//    private fun setUserInfo() {
//        viewModel.getUserById()
//        viewModel.loadingState.observe(this) { isLoading ->
//            binding.pbPost.isVisible = isLoading
//        }
//
//        viewModel.errorState.observe(this) { errorData ->
//            binding.tvError.isVisible = errorData.first
//            errorData.second?.message?.let {
//                binding.tvError.text = it
//            }
//        }
//        viewModel._accountData.observe(this){
//            binding.tvUsername.text = it.data?.username
//            binding.tvEmail.text = it.data?.email
//            binding.tvFirstName.text = it.data?.firstName.toString()
//            binding.tvLastName.text = it.data?.lastName.toString()
//            binding.tvAddress.text = it.data?.address.toString()
//            binding.tvPhone.text = it.data?.id.toString()
//        }
//
//    }






















