package com.ace.c11flight.di

import android.content.Context
import androidx.room.Room
import com.ace.c11flight.data.local.AppDatabase
import com.ace.c11flight.data.local.user.AccountDao
import com.ace.c11flight.data.local.wishlist.WishlistDao
import com.ace.c11flight.data.local.wishlist.WishlistDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WishlistDbModule {
    @Provides
    fun provideWishlistDao(wishlistDatabase: WishlistDatabase): WishlistDao {
        return wishlistDatabase.wishlistDao
    }

    @Provides
    @Singleton
    fun provideWishlistDb(@ApplicationContext appContext: Context):
            WishlistDatabase {
        return Room.databaseBuilder(
            appContext,
            WishlistDatabase::class.java,
            "wishlist_database"
        ).build()
    }
}