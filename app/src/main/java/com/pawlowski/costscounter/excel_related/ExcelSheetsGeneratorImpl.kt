package com.pawlowski.costscounter.excel_related

import com.pawlowski.costscounter.models.CostItem
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook

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
        headerRow.createCell(2).setCellValue("Ilość")
        headerRow.getCell(0).cellStyle = stylesUtil.boldWithBorderStyle
        headerRow.getCell(1).cellStyle = stylesUtil.boldWithBorderStyle
        headerRow.getCell(2).cellStyle = stylesUtil.boldWithBorderStyle

        for(i in items.indices)
        {
            val item = items[i]
            val row = sheet.createRow(i+1)
            row.createCell(0).setCellValue(item.name)
            row.createCell(1).setCellValue(item.cost)
            row.createCell(2).setCellValue(item.amount.toDouble())
        }

        val summaryRow = sheet.createRow(items.size+1)

        val cellDescription = summaryRow.createCell(1)
        cellDescription.setCellValue("Łącznie")
        val cellSum = summaryRow.createCell(2)
        cellSum.cellFormula = "SUMPRODUCT(B2:B${items.size+1},C2:C${items.size+1})"

        cellDescription.cellStyle = stylesUtil.boldAndHighlightedStyle
        cellSum.cellStyle = stylesUtil.boldAndHighlightedStyle
    }




}