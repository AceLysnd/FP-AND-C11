package com.ace.c11flight.data.model


import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("baggage")
    val baggage: String?,
    @SerializedName("cabin_baggage")
    val cabinBaggage: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("desc")
    val desc: String?,
    @SerializedName("Flight")
    val flight: Flight?,
    @SerializedName("flight_id")
    val flightId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("user")
    val user: User?
)