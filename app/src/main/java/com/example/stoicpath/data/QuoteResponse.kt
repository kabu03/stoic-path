package com.example.stoicpath.data

data class QuoteResponse(
    val data: QuoteData
)

data class QuoteData(
    val author: String,
    val quote: String
)
