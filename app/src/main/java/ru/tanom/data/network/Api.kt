package ru.tanom.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.tanom.data.model.Ads

interface Api {

    @GET("ads/list")
    suspend fun getAdsList(): List<Ads>


    @GET("ads/one/{id}")
    suspend fun getAdsOne(@Path("id") id: Int): Ads
}
