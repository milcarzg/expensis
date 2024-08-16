package com.cardlay.expensis.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardlay.expensis.model.CardModel
import com.cardlay.expensis.network.GetCardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val getCardsUseCase: GetCardsUseCase): ViewModel() {
        private val _cards = MutableStateFlow(emptyList<CardModel>())
    val cards: StateFlow<List<CardModel>> get()= _cards

    init {
        getCards()
    }

    fun onRefreshCardsEvent() {
        getCards()
    }

    private fun getCards() {
        viewModelScope.launch {
            try {
                val cards = getCardsUseCase()
                Log.d("CardViewModel", "Cards: ${cards.size}")
                Log.d("CardViewModel", "Cards: $cards")
                _cards.value = cards
            } catch (e: Exception) {
                Log.e("CardViewModel", "Error fetching cards", e)
                // Handle error
            }
        }
    }
}