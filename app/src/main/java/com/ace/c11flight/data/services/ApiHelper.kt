package com.ace.c11flight.data.services

import com.ace.c11flight.data.model.LoginInfo
import com.ace.c11flight.data.model.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiHelper {
    fun registerUser(userData: UserInfo, onResult: (UserInfo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(AccountApiService::class.java)
        retrofit.registerUser(userData).enqueue(
            object : Callback<UserInfo> {
                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<UserInfo>, response: Response<UserInfo>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun loginUser(loginData: LoginInfo, onResult: (LoginInfo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(AccountApiService::class.java)
        retrofit.loginUser(loginData).enqueue(
            object : Callback<LoginInfo> {
                override fun onFailure(call: Call<LoginInfo>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<LoginInfo>, response: Response<LoginInfo>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}