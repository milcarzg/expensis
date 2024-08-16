package com.cardlay.expensis.network

import com.cardlay.expensis.model.CardModel

data class CardItem(
    val available: Double,
    val currency: String,
    val id: String,
    val limit: Int,
    val name: String,
    val pan: String
)

fun CardModel.toCardItem() = CardItem(available, currency, id, limit, name, pan)