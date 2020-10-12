package ru.tanom.model.network

import retrofit2.http.GET
import retrofit2.http.Path
import ru.tanom.model.network.dto.Ad

interface ApiInterface {

    @GET("ad/list")
    suspend fun getAdsList(): BaseResponse<List<Ad>>

    @GET("ad/one/{id}")
    suspend fun getAd(@Path("id") id: Int?): BaseResponse<Ad>
}
