package com.ace.c11flight.data.local.network

import okhttp3.Interceptor

class OAuthInterceptor (private val accessToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "Bearer $accessToken").build()

        return chain.proceed(request)
    }
}