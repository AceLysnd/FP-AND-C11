package com.ace.c11flight.data.model


import com.google.gson.annotations.SerializedName

data class NotificationData(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("isRead")
    val isRead: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("Transaction")
    val transaction: Transaction?,
    @SerializedName("transaction_id")
    val transactionId: Int?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("User")
    val user: User?,
    @SerializedName("user_id")
    val userId: Int?
)