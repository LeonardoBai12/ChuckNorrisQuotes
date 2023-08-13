package io.lb.chucknorrisquotes.data.repository

import io.lb.chucknorrisquotes.data.remote.QuoteService
import io.lb.chucknorrisquotes.data.remote.dto.toQuote
import io.lb.chucknorrisquotes.domain.model.Quote
import io.lb.chucknorrisquotes.domain.model.Resource
import io.lb.chucknorrisquotes.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuoteRepositoryImpl(
    private val service: QuoteService
) : QuoteRepository {
    override suspend fun getRandomQuote(): Flow<Resource<Quote?>> = flow {
        emit(Resource.Loading(true))

        service.getQuote()?.toQuote()?.let { quote ->
            emit(Resource.Success(quote))
        } ?: emit(Resource.Error("Couldn't load data"))

        emit(Resource.Loading(false))
    }
}
