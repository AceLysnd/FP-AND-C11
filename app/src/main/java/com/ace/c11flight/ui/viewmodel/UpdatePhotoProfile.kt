package com.ace.c11flight.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ace.c11flight.data.local.network.RetrofitClient
import com.ace.c11flight.data.local.pojo.PhotoData
import com.ace.c11flight.data.local.pojo.ProfileResponse
import com.ace.c11flight.data.local.pojo.UpdatePhotoResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdatePhotoProfile: ViewModel() {

    var profileImageLiveData : MutableLiveData<UpdatePhotoResponse> = MutableLiveData()
    var profileLiveData : MutableLiveData<ProfileResponse> = MutableLiveData()

    fun getProfileData(): MutableLiveData<ProfileResponse> {
        return profileLiveData
    }

    fun putProfileImageData(): MutableLiveData<UpdatePhotoResponse> {
        return profileImageLiveData
    }

    fun putProfileImage(token: String,id:Long, file : MultipartBody.Part) {
        RetrofitClient.apiWithToken(token).putProfileImage(id, file)
            .enqueue(object : Callback<UpdatePhotoResponse> {
                override fun onResponse(
                    call: Call<UpdatePhotoResponse>,
                    response: Response<UpdatePhotoResponse>
                ) {
                    if (response.isSuccessful) {
                        profileImageLiveData.postValue(response.body())
                    } else {
                        Log.d("failed putphoto", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<UpdatePhotoResponse>, t: Throwable) {
                    Log.d("on failure", call.toString())
                }
            })
    }

    fun callProfileApi(token : String) {
        RetrofitClient.apiWithToken(token).getProfile()
            .enqueue(object : Callback<ProfileResponse> {
                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {
                    if (response.isSuccessful) {
                        profileLiveData.postValue(response.body())
                        Log.d("Fetch Profile Data ", response.body().toString())
                    } else {
                        Log.d("Fetch Profile Data ", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Log.d("Fetch Profile Data ", call.toString())
                }

            })
    }
}