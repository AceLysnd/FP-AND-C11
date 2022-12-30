package com.ace.c11flight.data.model


import com.google.gson.annotations.SerializedName

data class TransactionListResponse(
    @SerializedName("data")
    val data: List<TransactionData>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("total")
    val total: Int?
)