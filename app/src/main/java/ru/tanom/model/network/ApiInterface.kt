package ru.tanom.model.network

import retrofit2.http.GET
import retrofit2.http.Path
import ru.tanom.model.network.dto.Ad

interface ApiInterface {

    @GET("ads/list")
    suspend fun getAdsList(): List<Ad>

    @GET("ads/one/{id}")
    suspend fun getAd(@Path("id") id: Int?): Ad
}
