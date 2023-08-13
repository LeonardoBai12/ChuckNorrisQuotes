package io.lb.chucknorrisquotes.domain.repository

import io.lb.chucknorrisquotes.domain.model.Quote
import io.lb.chucknorrisquotes.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    suspend fun getRandomQuote(): Flow<Resource<Quote?>>
}
