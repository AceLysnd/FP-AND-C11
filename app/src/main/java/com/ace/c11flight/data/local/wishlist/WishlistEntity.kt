package com.ace.c11flight.data.local.wishlist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist")
data class WishlistEntity(
    @PrimaryKey(autoGenerate = true)
    var wishlistId: Long = 0,

    @ColumnInfo(name = "fromCode")
    var fromCode: String?,

    @ColumnInfo(name = "toCode")
    var toCode: String?,

    @ColumnInfo(name = "fromCity")
    var fromCity: String?,

    @ColumnInfo(name = "toCity")
    var toCity: String?,

    @ColumnInfo(name = "price")
    var price: String?,

    @ColumnInfo(name = "promoId")
    var promoId: Int?,

    @ColumnInfo(name = "TicketId")
    var ticketId: Int?,
)