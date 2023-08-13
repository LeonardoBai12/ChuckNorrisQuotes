package io.lb.chucknorrisquotes.data.remote.dto

import io.lb.chucknorrisquotes.domain.model.Quote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteResponse(
    val id: String,
    val value: String,
    @SerialName("icon_url")
    val iconUrl: String
)

fun QuoteResponse.toQuote() = Quote(
    quote = value,
    iconUrl = iconUrl
)
