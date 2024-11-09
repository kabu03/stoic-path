package hu.bme.aut.qh8vdn.stoicpath.data

data class QuoteResponse(
    val data: QuoteData
)

data class QuoteData(
    val author: String,
    val quote: String
)
