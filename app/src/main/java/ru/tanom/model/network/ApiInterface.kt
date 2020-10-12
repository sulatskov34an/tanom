package ru.tanom.model.network

import retrofit2.http.GET
import retrofit2.http.Path
import ru.tanom.model.network.dto.String

interface ApiInterface {

    @GET("ad/list")
    suspend fun getAdsList(): BaseResponse<List<String>>

    @GET("ad/one/{id}")
    suspend fun getAd(@Path("id") id: Int?): BaseResponse<String>
}
