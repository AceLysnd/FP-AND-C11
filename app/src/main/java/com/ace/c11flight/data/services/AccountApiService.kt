package com.ace.c11flight.data.services

import android.service.autofill.UserData
import com.ace.c11flight.data.model.*
import com.ace.c11flight.data.services.ServiceBuilder.BASE_URL
import com.ace.c11flight.ui.view.HomeActivity.Companion.TOKEN
import com.ace.c11flight.ui.view.PromoListActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface AccountApiService {

    @Headers("Content-Type: application/json")
    @POST("register")
    fun registerUser(
        @Body userData: UserInfo
    ): Call<UserInfo>

    @POST("login")
    fun loginUser(
        @Body loginData: LoginInfo
    ): Call<LoginInfo>

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: Long,
    ): AccountResponse

    @PUT("users/{id}")
    suspend fun updateUserById(
        @Path("id") id: Long,
        @Body data: RequestBody,
        @Header("Authorization") authorization: String = TOKEN
    ): AccountResponse

    @GET("airport")
    suspend fun getAirportList(
    ): AirportListResponse

    @GET("promo")
    suspend fun getPromoList(
    ): PromoListResponse

    @GET("ticket")
    suspend fun getTickets(
    ): TicketListResponse

    @GET("ticket/{id}")
    suspend fun getTicketById(
        @Path("id") id: Int,
    ): TicketResponse

    @POST("transaction")
    suspend fun createTransaction(
        @Body transaction: RequestBody,
        @Header("Authorization") authorization: String = TOKEN
    ): Transaction

    @GET("transaction")
    suspend fun getTransactionList(
        @Header("Authorization") authorization: String = TOKEN
    ): TransactionListResponse

    @GET("transaction/{id}")
    suspend fun getTransactionById(
        @Path("id") id: Int?,
        @Header("Authorization") authorization: String = TOKEN
    ): CreateTransactionResponse

    @GET("notification/user/{id}")
    suspend fun getNotificationById(
        @Path("id") id: Long?,
        @Header("Authorization") authorization: String = TOKEN
    ): NotificationResponse

    companion object{

        @JvmStatic
        operator fun invoke() : AccountApiService{
            val authInterceptor = Interceptor{
                val originRequest = it.request()
                val newUrl = originRequest.url.newBuilder().apply {
                }.build()
                it.proceed(originRequest.newBuilder().url(newUrl).build())
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(AccountApiService::class.java)
        }
    }
}