package com.ace.c11flight.data.local.pojo


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("data")
    val `data`: DataX?,
    @SerializedName("status")
    val status: String?
)