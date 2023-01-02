package com.ace.c11flight.data.local.wishlist

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ace.c11flight.data.local.user.AccountDao
import com.ace.c11flight.data.local.user.AccountEntity

@Database(entities = [WishlistEntity::class], version = 4, exportSchema = false)
abstract class WishlistDatabase : RoomDatabase() {

    abstract val wishlistDao : WishlistDao
}