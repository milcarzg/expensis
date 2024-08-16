package com.cardlay.expensis.network

import com.cardlay.expensis.model.CardModel
import com.cardlay.expensis.repository.CardRepository
import javax.inject.Inject

class GetCardsUseCase @Inject constructor( private val cardsRepository: CardRepository) {

    suspend operator fun invoke(): List<CardModel> {
        return cardsRepository.getCards()
    }
}
