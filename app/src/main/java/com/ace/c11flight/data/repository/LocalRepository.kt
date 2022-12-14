package com.ace.c11flight.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.ace.c11flight.data.local.user.AccountDataSource
import com.ace.c11flight.data.local.user.AccountEntity
import com.ace.c11flight.data.local.wishlist.WishlistDataSource
import com.ace.c11flight.data.local.wishlist.WishlistEntity
import com.ace.c11flight.data.model.Account
import com.ace.c11flight.data.model.AccountDataStoreManager
import com.ace.c11flight.data.model.Prefs
import javax.inject.Inject

class LocalRepository @Inject constructor (
    private val accountDataSource: AccountDataSource,
    private val prefs: AccountDataStoreManager,
    private val wishlistDataSource: WishlistDataSource
) {
    suspend fun insertWishlist(wishlistEntity: WishlistEntity):Long {
        return wishlistDataSource.insertWishlist(wishlistEntity)
    }

    suspend fun deleteWishlist(wishlistEntity: WishlistEntity): Int {
        return wishlistDataSource.deleteWishlist(wishlistEntity)
    }

    suspend fun getAccountById(id: Long): AccountEntity? {
        return accountDataSource.getAccountById(id)
    }

    suspend fun createAccount(account: AccountEntity): Long {
        return accountDataSource.registerAccount(account)
    }

    suspend fun updateAccount(account: AccountEntity): Int {
        return accountDataSource.updateAccount(account)
    }

    suspend fun getAccount(username: String): AccountEntity {                                   
        return accountDataSource.getUser(username)
    }

    suspend fun setAccount(username: String, email: String, password:String, id: Long, token: String) {
        prefs.setAccount(username, email, password, id, token)
    }

    suspend fun setLoginStatus(loginStatus: Boolean) {
        prefs.setLoginStatus(loginStatus)
    }

    suspend fun setInAppStatus(inAppStatus: Int) {
        prefs.setInAppStatus(inAppStatus)
    }

    suspend fun setProfilePicture(profilePicture: String) {
        prefs.setProfilePicture(profilePicture)
    }

    fun getAccountPrefs(): LiveData<Prefs> {
        return prefs.getAccount().asLiveData()
    }

    fun getLoginStatus(): LiveData<Boolean> {
        return prefs.getLoginStatus().asLiveData()
    }

    fun getInAppStatus(): LiveData<Int> {
        return prefs.getInAppStatus().asLiveData()
    }

    fun getAccountId(): LiveData<Long> {
        return prefs.getAccountId().asLiveData()
    }

    fun getProfilePicture(): LiveData<String> {
        return prefs.getProfilePicture().asLiveData()
    }
}