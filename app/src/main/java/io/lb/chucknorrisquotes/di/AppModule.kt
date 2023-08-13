package io.lb.chucknorrisquotes.di

import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import io.lb.chucknorrisquotes.data.remote.QuoteService
import io.lb.chucknorrisquotes.data.remote.QuoteServiceImpl
import io.lb.chucknorrisquotes.data.repository.QuoteRepositoryImpl
import io.lb.chucknorrisquotes.domain.repository.QuoteRepository
import kotlinx.serialization.json.Json

@InstallIn(ViewModelComponent::class)
object AppModule {
    fun providesQuoteService() : QuoteService {
        return QuoteServiceImpl(
            client = HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {
                    json(
                        Json {
                            ignoreUnknownKeys = true
                        }
                    )
                }
            }
        )
    }

    fun provideQuoteRepository(service: QuoteService) : QuoteRepository {
        return QuoteRepositoryImpl(
            service = service
        )
    }
}
