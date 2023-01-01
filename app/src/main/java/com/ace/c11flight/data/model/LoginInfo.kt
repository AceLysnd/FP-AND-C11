package com.ace.c11flight.data.model

import com.google.gson.annotations.SerializedName

data class LoginInfo(
    @SerializedName("status") val status: String?,
    @SerializedName("id") val id:Long?,
    @SerializedName("username") val username: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("data") val data: Account?,
    @SerializedName("token") val token: String?
)
