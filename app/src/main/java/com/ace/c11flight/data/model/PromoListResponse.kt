package com.ace.c11flight.data.model


import com.google.gson.annotations.SerializedName

data class PromoListResponse(
    @SerializedName("data")
    val data: List<Promo>?,
    @SerializedName("status")
    val status: String?
)