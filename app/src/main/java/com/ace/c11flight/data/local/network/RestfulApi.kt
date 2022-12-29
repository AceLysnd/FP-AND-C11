package com.ace.c11flight.data.local.network

import com.ace.c11flight.data.local.pojo.ProfileResponse
import com.ace.c11flight.data.local.pojo.UpdatePhotoResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface RestfulApi {

    @Multipart
    @PUT("users/{id}")
    fun putProfileImage(@Path("id") id: Int,@Part file : MultipartBody.Part) : Call<UpdatePhotoResponse>

    @GET("user")
    fun getProfile() : Call<ProfileResponse>
}