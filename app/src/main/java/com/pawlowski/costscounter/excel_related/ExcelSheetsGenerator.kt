package com.pawlowski.costscounter.excel_related

import com.pawlowski.costscounter.CostItem

interface ExcelSheetsGenerator {
    fun addSheetWithCostData(items: List<CostItem>, sheetName: String)
}