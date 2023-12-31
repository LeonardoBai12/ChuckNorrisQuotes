package io.lb.chucknorrisquotes.data.remote

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.lb.chucknorrisquotes.data.remote.dto.QuoteResponse
import java.lang.Exception

class QuoteServiceImpl(
    private val client: HttpClient
) : QuoteService {
    override suspend fun getQuote(): QuoteResponse? {
        return try {
            client.get {
                url(HttpRoutes.RANDOM_QUOTE)
            }.body<QuoteResponse>()
        } catch (e: RedirectResponseException) {
            Log.d("QuoteServiceImpl/error", e.response.status.description)
            null
        } catch (e: ClientRequestException) {
            Log.d("QuoteServiceImpl/error", e.response.status.description)
            null
        } catch (e: ServerResponseException) {
            Log.d("QuoteServiceImpl/error", e.response.status.description)
            null
        } catch (e: Exception) {
            Log.d("QuoteServiceImpl/error", e.message ?: "")
            null
        }
    }
}
