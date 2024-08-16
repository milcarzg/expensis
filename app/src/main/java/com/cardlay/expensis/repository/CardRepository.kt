package com.cardlay.expensis.repository

import com.cardlay.expensis.model.CardModel
import com.cardlay.expensis.network.CardsService
import javax.inject.Inject

class CardRepository @Inject constructor(private val cardsService: CardsService) {
    suspend fun getCards(): List<CardModel> {
        return cardsService.getCards().map {
            it
        }
    }
}