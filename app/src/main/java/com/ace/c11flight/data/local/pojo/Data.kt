package com.ace.c11flight.data.local.pojo


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("photo")
    val photo: String?
)