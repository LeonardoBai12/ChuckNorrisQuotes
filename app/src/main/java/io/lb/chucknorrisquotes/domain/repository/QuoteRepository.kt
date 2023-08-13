package io.lb.chucknorrisquotes.domain.repository

import io.lb.chucknorrisquotes.domain.model.Quote

interface QuoteRepository {
    suspend fun getRandomQuote(): Quote?
}
