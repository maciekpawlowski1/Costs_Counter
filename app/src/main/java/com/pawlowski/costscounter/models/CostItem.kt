package com.pawlowski.costscounter.models


data class CostItem(
    val itemId: Int,
    val name: String,
    val cost: Double,
    val amount: Int
    )