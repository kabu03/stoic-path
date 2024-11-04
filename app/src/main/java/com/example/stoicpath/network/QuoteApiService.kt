package com.example.stoicpath.network

import com.example.stoicpath.data.QuoteResponse
import retrofit2.Call
import retrofit2.http.GET

interface QuoteApiService {

    @GET("stoic-quote")
    fun getDailyQuote(): Call<QuoteResponse>
}
