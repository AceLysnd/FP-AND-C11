package com.ace.c11flight.data.services

class TempApiHelper(private val api: AccountApiService) {
    suspend fun getUserById(
        id: Long
    ) = api.getUserById(id)
}