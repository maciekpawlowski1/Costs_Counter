package com.pawlowski.costscounter.excel_related

import org.apache.poi.ss.usermodel.Workbook

interface ExcelSaver {
    fun save(fileName: String, workBook: Workbook)
}