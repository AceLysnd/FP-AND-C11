package com.ace.c11flight.ui.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ace.c11flight.data.local.AppDatabase
import com.ace.c11flight.data.local.user.AccountDao
import com.ace.c11flight.data.local.user.AccountDataSource
import com.ace.c11flight.data.local.user.AccountEntity
import com.ace.c11flight.data.local.wishlist.WishlistDao
import com.ace.c11flight.data.local.wishlist.WishlistDataSource
import com.ace.c11flight.data.local.wishlist.WishlistDatabase
import com.ace.c11flight.data.model.AccountDataStoreManager
import com.ace.c11flight.data.repository.LocalRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RegisterViewModelTest {

    private lateinit var localRepo: LocalRepository
    private lateinit var viewModel: RegisterActivityViewModel
    private lateinit var accountDataSource: AccountDataSource
    private lateinit var wishlistDao: WishlistDao
    private lateinit var wishlistDataSource: WishlistDataSource
    private lateinit var prefs: AccountDataStoreManager
    private lateinit var dao: AccountDao
    private lateinit var databaseW: WishlistDatabase
    private lateinit var database: AppDatabase
    val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()      //executes each task synchronously

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
        viewModel = RegisterActivityViewModel(localRepo)
    }

    @Test
    fun registerUser(): Unit = runBlocking {
        val account = AccountEntity(1, "test", "test", "test")
        viewModel.registerUser(account)
        val getAccount = dao.getAllAccount()
        assertThat(getAccount.contains(account)).isTrue()
    }

    @After
    fun closeDb() {
        database.close()
    }
}