package com.ace.whatmovie.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ace.c11flight.data.local.AppDatabase
import com.ace.c11flight.data.local.wishlist.WishlistDao
import com.ace.c11flight.data.local.wishlist.WishlistDatabase
import com.ace.c11flight.data.local.wishlist.WishlistEntity
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var database: WishlistDatabase
    private lateinit var dao: WishlistDao

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(context, WishlistDatabase::class.java).build()
        dao = database.wishlistDao
    }

    @After
    fun closeDb(){
        database.close()
    }

    @Test
    fun createAndReadAccount(): Unit = runBlocking {
        val wishlist = WishlistEntity(1,"test","test","test","test","",0,0)
        dao.insertWishlist(wishlist)
        val accountById = dao.getWishlist()
        assertThat(accountById.contains(wishlist)).isTrue()
    }
}