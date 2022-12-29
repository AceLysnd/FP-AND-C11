package com.ace.c11flight.data.model


import com.google.gson.annotations.SerializedName

data class Promo(
    @SerializedName("code")
    val code: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("discount")
    val discount: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)