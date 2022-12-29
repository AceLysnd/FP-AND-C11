package com.ace.c11flight.data.local.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val UPDATE_BASE_URL_API = "https://beckend-takeoff-production-46fc.up.railway.app/api/v1/"

    fun apiWithToken(accessToken: String): RestfulApi {
        val client = OkHttpClient.Builder()
            .addInterceptor(OAuthInterceptor(accessToken))
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(UPDATE_BASE_URL_API)
            .client(client)
            .build()

        return retrofit.create(RestfulApi::class.java)
    }

    val apiInstance : RestfulApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(UPDATE_BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RestfulApi::class.java)
    }
}