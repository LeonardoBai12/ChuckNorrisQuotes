package io.lb.chucknorrisquotes.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lb.chucknorrisquotes.domain.model.Resource
import io.lb.chucknorrisquotes.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val repository: QuoteRepository
) : ViewModel() {
    private val _state = mutableStateOf(QuoteState())
    val state: State<QuoteState> = _state

    init {
        getRandomQuote()
    }
    fun getRandomQuote() {
        viewModelScope.launch {
            repository.getRandomQuote().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            quote = result.data,
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = result.isLoading,
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            quote = null,
                        )
                    }
                }
            }
        }
    }
}
