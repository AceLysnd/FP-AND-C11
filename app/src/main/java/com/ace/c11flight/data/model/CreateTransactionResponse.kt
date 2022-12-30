package com.ace.c11flight.data.model


import com.google.gson.annotations.SerializedName

data class CreateTransactionResponse(
    @SerializedName("data")
    val data: Transaction?,
    @SerializedName("status")
    val status: String?
)