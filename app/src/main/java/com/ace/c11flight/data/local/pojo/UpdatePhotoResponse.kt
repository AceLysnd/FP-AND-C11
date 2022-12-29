package com.ace.c11flight.data.local.pojo


import com.google.gson.annotations.SerializedName

data class UpdatePhotoResponse(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("status")
    val status: String?
)