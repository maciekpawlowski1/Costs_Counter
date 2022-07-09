package com.pawlowski.costscounter.excel_related

import com.pawlowski.costscounter.models.CostItem
import org.apache.poi.ss.usermodel.*

class ExcelSheetsGeneratorImpl(private val workBook: Workbook, private val stylesUtil: ExcelStylesUtil) :
    ExcelSheetsGenerator {
    private val sheets: MutableList<Sheet> = mutableListOf()


    private fun addNewSheet(sheetName: String)
    {
        val sheet = workBook.createSheet(sheetName)
        sheets.add(sheet)
    }

    override fun addSheetWithCostData(items: List<CostItem>, sheetName: String)
    {
        addNewSheet(sheetName)
        val sheet = sheets.last()

        val headerRow = sheet.createRow(0)
        headerRow.createCell(0).setCellValue("Rzecz")
        headerRow.createCell(1).setCellValue("Koszt")
        headerRow.getCell(0).cellStyle = stylesUtil.boldWithBorderStyle
        headerRow.getCell(1).cellStyle = stylesUtil.boldWithBorderStyle


        for(i in items.indices)
        {
            val item = items[i]
            val row = sheet.createRow(i+1)
            row.createCell(0).setCellValue(item.name)
            row.createCell(1).setCellValue(item.cost)
        }

        val summaryRow = sheet.createRow(items.size+1)

        val cellDescription = summaryRow.createCell(0)
        cellDescription.setCellValue("Łącznie")
        val cellSum = summaryRow.createCell(1)
        cellSum.cellFormula = "SUM(B2:B${items.size+1})"

        cellDescription.cellStyle = stylesUtil.boldAndHighlightedStyle
        cellSum.cellStyle = stylesUtil.boldAndHighlightedStyle
    }




}