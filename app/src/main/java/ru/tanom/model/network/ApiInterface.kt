package ru.tanom.model.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.tanom.model.network.dto.Ad
import ru.tanom.model.network.dto.LoginRequest

interface ApiInterface {

    @GET("ad/list")
    suspend fun getAdsList(): BaseResponse<List<Ad>>

    @GET("ad/one/{id}")
    suspend fun getAd(@Path("id") id: Int?): BaseResponse<Ad>

    @POST("users/signin")
    suspend fun login(@Body loginRequest: LoginRequest): BaseResponse<String>

}
