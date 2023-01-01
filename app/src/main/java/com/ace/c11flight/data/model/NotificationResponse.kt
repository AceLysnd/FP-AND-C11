package com.ace.c11flight.data.model


import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("data")
    val `data`: List<NotificationData>?,
    @SerializedName("status")
    val status: String?
)