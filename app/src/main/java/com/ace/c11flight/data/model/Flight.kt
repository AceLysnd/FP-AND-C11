package com.ace.c11flight.data.model


import com.google.gson.annotations.SerializedName

data class Flight(
    @SerializedName("arrival_time")
    val arrivalTime: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("departure_time")
    val departureTime: String?,
    @SerializedName("from")
    val from: From?,
    @SerializedName("from_airport_id")
    val fromAirportId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("Plane")
    val plane: Plane?,
    @SerializedName("plane_id")
    val planeId: Int?,
    @SerializedName("to")
    val to: To?,
    @SerializedName("to_airport_id")
    val toAirportId: Int?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)