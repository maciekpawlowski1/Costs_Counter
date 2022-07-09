package com.pawlowski.costscounter.excel_related

import com.pawlowski.costscounter.models.CostItem

interface ExcelSheetsGenerator {
    fun addSheetWithCostData(items: List<CostItem>, sheetName: String)
}