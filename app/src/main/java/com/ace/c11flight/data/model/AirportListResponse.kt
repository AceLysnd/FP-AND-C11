package com.ace.c11flight.data.model


import com.google.gson.annotations.SerializedName

data class AirportListResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("data")
    val data: List<Airport>?,
    @SerializedName("status")
    val status: String?
)