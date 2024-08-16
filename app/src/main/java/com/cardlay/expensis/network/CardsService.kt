package com.cardlay.expensis.network

import com.cardlay.expensis.model.CardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CardsService @Inject constructor(private val cardsApi: CardsApi){
    suspend fun getCards(): List<CardModel> {
        return withContext(Dispatchers.IO) {
            val cards = cardsApi.getCards()
            cards.body() ?: emptyList()
        }
    }
}