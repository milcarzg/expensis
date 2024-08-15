package com.cardlay.expensis.model

data class Card(
    val shortPan: String,
    val availableBalance: String,
    val creditLimit: String,
    val currency: String,
    val type: Type,
)

enum class Type {
    Physical,
    Virtual
}