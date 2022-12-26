package com.ace.c11flight.data.model


import com.google.gson.annotations.SerializedName

data class Airport(
    @SerializedName("city")
    val city: String?,
    @SerializedName("city_code")
    val cityCode: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)