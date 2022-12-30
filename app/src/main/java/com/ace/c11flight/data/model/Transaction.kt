package com.ace.c11flight.data.model


import com.google.gson.annotations.SerializedName

data class Transaction(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("promo_id")
    val promoId: Any?,
    @SerializedName("ticket_id")
    val ticketId: Any?,
    @SerializedName("total")
    val total: Any?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("user_id")
    val userId: Any?
)