package com.ace.c11flight.data.model


import com.google.gson.annotations.SerializedName

data class TransactionData(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("Notifications")
    val notifications: List<Notification>?,
    @SerializedName("Promo")
    val promo: Promo?,
    @SerializedName("promo_id")
    val promoId: Int?,
    @SerializedName("Ticket")
    val ticket: Ticket?,
    @SerializedName("ticket_id")
    val ticketId: Int?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("User")
    val user: User?,
    @SerializedName("user_id")
    val userId: Int?
)