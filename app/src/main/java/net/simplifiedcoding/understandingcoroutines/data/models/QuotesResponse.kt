package net.simplifiedcoding.understandingcoroutines.data.models

data class QuotesResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)