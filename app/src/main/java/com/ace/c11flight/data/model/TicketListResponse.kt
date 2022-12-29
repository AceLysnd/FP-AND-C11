package com.ace.c11flight.data.model


import com.google.gson.annotations.SerializedName

data class TicketListResponse(
    @SerializedName("data")
    val data: List<Ticket>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("total")
    val total: Int?
)