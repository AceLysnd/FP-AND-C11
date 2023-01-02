package com.ace.c11flight.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ace.c11flight.data.local.wishlist.WishlistDao
import com.ace.c11flight.data.local.wishlist.WishlistEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    val wishlistDao: WishlistDao
): ViewModel() {
    val _wishlistResult = MutableLiveData<List<WishlistEntity>>()
    val wishlistResult: LiveData<List<WishlistEntity>>
        get() =_wishlistResult

    val loadingState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<Pair<Boolean, Exception?>>()

    fun getWishlist(){
        loadingState.postValue(true)
        errorState.postValue(Pair(false, null))
        viewModelScope.launch {
            try {
                val data = wishlistDao.getWishlist()
                viewModelScope.launch {
                    _wishlistResult.postValue(data)
                    loadingState.postValue(false)
                    errorState.postValue(Pair(false,null))
                }
            } catch (e: Exception) {
                viewModelScope.launch {
                    loadingState.postValue(false)
                    errorState.postValue(Pair(true,e))
                }
            }
        }
    }

    fun deleteWishlist(item: WishlistEntity){
        loadingState.postValue(true)
        errorState.postValue(Pair(false, null))
        viewModelScope.launch {
            try {
                val data = wishlistDao.deleteWishlist(item)
                viewModelScope.launch {
                    loadingState.postValue(false)
                    errorState.postValue(Pair(false,null))
                }
            } catch (e: Exception) {
                viewModelScope.launch {
                    loadingState.postValue(false)
                    errorState.postValue(Pair(true,e))
                }
            }
        }
    }

//    fun getTickets() {
//        loadingState.postValue(true)
//        errorState.postValue(Pair(false, null))
//        viewModelScope.launch {
//            try {
//                val data = wishlistDao.getWishlist()
//                viewModelScope.launch {
//                    _wishlistResult.postValue(data)
//                    loadingState.postValue(false)
//                    errorState.postValue(Pair(false,null))
//                }
//            } catch (e: Exception) {
//                viewModelScope.launch {
//                    loadingState.postValue(false)
//                    errorState.postValue(Pair(true,e))
//                }
//            }
//        }
//    }
}