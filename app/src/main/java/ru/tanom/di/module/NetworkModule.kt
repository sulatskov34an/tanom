package ru.tanom.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tanom.common.AppConst
import ru.tanom.model.network.ApiInterface

@Module(includes = [OkHttpClientModule::class])
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideTanomApi(retrofit: Retrofit) = retrofit.create(ApiInterface::class.java)

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConst.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGsonConverterFactory() = GsonConverterFactory.create()

}