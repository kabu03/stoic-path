package hu.bme.aut.qh8vdn.stoicpath.network

import hu.bme.aut.qh8vdn.stoicpath.data.QuoteResponse
import retrofit2.Call
import retrofit2.http.GET

interface QuoteApiService {

    @GET("stoic-quote")
    fun getDailyQuote(): Call<QuoteResponse>
}
