package io.lb.chucknorrisquotes.data.remote

import io.lb.chucknorrisquotes.data.remote.dto.QuoteResponse

interface QuoteService {
    suspend fun getQuote(): QuoteResponse?
}
