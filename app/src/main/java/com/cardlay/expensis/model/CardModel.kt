package com.cardlay.expensis.model

data class CardModel(
    val available: Double,
    val currency: String,
    val id: String,
    val limit: Int,
    val name: String,
    val pan: String
)