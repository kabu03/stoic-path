package hu.bme.aut.qh8vdn.stoicpath.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://stoic.tekloon.net/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val quoteApiService: QuoteApiService by lazy {
        retrofit.create(QuoteApiService::class.java)
    }
}
