package com.ace.c11flight.data.local.wishlist

import com.google.gson.annotations.SerializedName

data class WishlistResponse(
    @SerializedName("data")
    val data: List<WishlistEntity>?
)
