package io.lb.chucknorrisquotes.presentation

import io.lb.chucknorrisquotes.domain.model.Quote

data class QuoteState(
    val quote: Quote? = null,
    val isLoading: Boolean = true
)
