package io.lb.chucknorrisquotes.data.repository

import io.lb.chucknorrisquotes.data.remote.QuoteService
import io.lb.chucknorrisquotes.data.remote.dto.toQuote
import io.lb.chucknorrisquotes.domain.repository.QuoteRepository

class QuoteRepositoryImpl(
    private val service: QuoteService
) : QuoteRepository {
    override suspend fun getRandomQuote() = service.getQuote()?.toQuote()
}
