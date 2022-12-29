package com.ace.c11flight.data.model


import com.google.gson.annotations.SerializedName

data class Plane(
    @SerializedName("capacity")
    val capacity: Int?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)