package ru.tanom.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.tanom.data.ResponseWrapper
import ru.tanom.data.model.Users

interface Api {
    @GET("5dcc12d554000064009c20fc")
    suspend fun getUsers(
        @Query("page") page: Int
    ): ResponseWrapper<Users>


    @GET("5dcc147154000059009c2104")
    suspend fun getUsersError(
        @Query("page") page: Int
    ): ResponseWrapper<Users>
}
