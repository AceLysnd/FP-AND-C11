package com.ace.c11flight.data.local.wishlist

import javax.inject.Inject

class WishlistDataSource @Inject constructor(private val wishlistDao: WishlistDao) {


    suspend fun insertWishlist(wishlistEntity: WishlistEntity): Long{
        return wishlistDao.insertWishlist(wishlistEntity)
    }

    suspend fun deleteWishlist(wishlistEntity: WishlistEntity): Int {
        return wishlistDao.deleteWishlist(wishlistEntity)
    }
}