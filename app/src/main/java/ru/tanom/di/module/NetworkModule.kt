package ru.tanom.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tanom.common.AppConst
import ru.tanom.model.network.ApiInterface
import javax.inject.Singleton

@Module(includes = [OkHttpClientModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideTanomApi(retrofit: Retrofit) = retrofit.create(ApiInterface::class.java)

    @Provides
    @Singleton
    fun provideRetrofitInterface(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConst.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

}