package com.example.calconverter.converter.networkapi

import com.example.calconverter.converter.model.Rates
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_ACCESS_KEY = "35787cd8c3aedaff06c646b5af6becf0"

interface FixerApiService {
    @GET("latest?access_key=$API_ACCESS_KEY")
    suspend fun getExchangeRates(@Query("base") currencyToConvert: String): Rates
}