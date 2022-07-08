package com.pawlowski.costscounter

data class CostsReport(
    private val costItems: List<CostItem>,
    private val collectionId: Int,
    private val collectionName: String
)