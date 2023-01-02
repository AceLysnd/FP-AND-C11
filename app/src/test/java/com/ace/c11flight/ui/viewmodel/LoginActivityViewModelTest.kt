package com.ace.whatmovie.ui.viewmodel

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ace.c11flight.data.local.AppDatabase
import com.ace.c11flight.data.local.user.AccountDao
import com.ace.c11flight.data.local.user.AccountDataSource
import com.ace.c11flight.data.local.wishlist.WishlistDao
import com.ace.c11flight.data.local.wishlist.WishlistDataSource
import com.ace.c11flight.data.local.wishlist.WishlistDatabase
import com.ace.c11flight.data.model.AccountDataStoreManager
import com.ace.c11flight.data.repository.LocalRepository
import com.ace.c11flight.ui.viewmodel.LoginActivityViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {

    private lateinit var localRepo: LocalRepository
    private lateinit var viewModel: LoginActivityViewModel
    private lateinit var accountDataSource: AccountDataSource
    private lateinit var wishlistDataSource: WishlistDataSource
    private lateinit var prefs: AccountDataStoreManager
    private lateinit var dao: AccountDao
    private lateinit var wishlistDao: WishlistDao
    private lateinit var database: AppDatabase
    private lateinit var databaseW: WishlistDatabase
    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        // Ini database per test harus dibikin terus karena repo butuh account datasource
        database =
            Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries()
                .build()
        dao = database.accountDao
        databaseW =
            Room.inMemoryDatabaseBuilder(context, WishlistDatabase::class.java).allowMainThreadQueries()
                .build()
        wishlistDao = databaseW.wishlistDao

        Dispatchers.setMain(dispatcher)
        accountDataSource = AccountDataSource(dao)
        wishlistDataSource = WishlistDataSource(wishlistDao)
        prefs = AccountDataStoreManager(context)
        localRepo = LocalRepository(accountDataSource, prefs,wishlistDataSource)
        viewModel = LoginActivityViewModel(localRepo)
    }

    @Test
    fun saveLoginStatus(): Unit = runBlocking  {
        val loginStatus = true
        viewModel.saveLoginStatus(loginStatus)
        val getLoginStatus = prefs.getLoginStatus()
        assertThat(getLoginStatus.equals(loginStatus))

    }
}