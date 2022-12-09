package com.ace.c11flight.data.model


import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("address")
    val address: Any?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("firstName")
    val firstName: Any?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("imgPassport")
    val imgPassport: Any?,
    @SerializedName("imgResidentPermit")
    val imgResidentPermit: Any?,
    @SerializedName("imgVisa")
    val imgVisa: Any?,
    @SerializedName("lastName")
    val lastName: Any?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("phone")
    val phone: Any?,
    @SerializedName("photo")
    val photo: Any?,
    @SerializedName("role")
    val role: String?,
    @SerializedName("token")
    val token: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("username")
    val username: String?
)