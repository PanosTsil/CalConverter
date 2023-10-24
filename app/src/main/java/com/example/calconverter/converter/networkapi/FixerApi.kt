package com.example.calconverter.converter.networkapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://data.fixer.io/api/"

object FixerApi {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: FixerApiService by lazy {
        retrofit.create(FixerApiService::class.java)
    }
}