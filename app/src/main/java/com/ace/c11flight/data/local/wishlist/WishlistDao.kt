package com.ace.c11flight.data.local.wishlist

import androidx.room.*
import com.ace.c11flight.data.local.user.AccountEntity

@Dao
interface WishlistDao {

    @Insert
    suspend fun insertWishlist(wishlistEntity: WishlistEntity): Long

    @Delete
    suspend fun deleteWishlist(wishlistEntity: WishlistEntity): Int

//    @Query("SELECT * FROM account_information WHERE username = :username")
//    suspend fun getUser(username: String) : AccountEntity
//
//    @Query("SELECT * FROM ACCOUNT_INFORMATION WHERE accountId == :id LIMIT 1")
//    suspend fun getAccountById(id : Long) : AccountEntity?
//
    @Query("SELECT * FROM WISHLIST")
    suspend fun getWishlist() : List<WishlistEntity>
}