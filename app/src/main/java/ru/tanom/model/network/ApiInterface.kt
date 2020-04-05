package ru.tanom.model.network

import retrofit2.http.GET
import retrofit2.http.Path
import ru.tanom.model.network.dto.Ads

interface ApiInterface {

    @GET("ads/list")
    suspend fun getAdsList(): List<Ads>


    @GET("ads/one/{id}")
    suspend fun getAdsOne(@Path("id") id: Int): Ads
}
