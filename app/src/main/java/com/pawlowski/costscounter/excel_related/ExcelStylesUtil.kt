package com.pawlowski.costscounter.excel_related

import org.apache.poi.ss.usermodel.*

class ExcelStylesUtil(workBook: Workbook) {
    val boldAndHighlightedStyle: CellStyle
    val boldStyle: CellStyle
    val boldWithBorderStyle: CellStyle

    init {
        // init boldAndHighlightedStyle
        boldAndHighlightedStyle = workBook.createCellStyle()
        val boldFont = workBook.createFont()
        boldFont.bold = true
        boldAndHighlightedStyle.fillForegroundColor = IndexedColors.YELLOW.index
        boldAndHighlightedStyle.fillPattern = FillPatternType.SOLID_FOREGROUND
        boldAndHighlightedStyle.setFont(boldFont)

        // init boldStyle
        boldStyle = workBook.createCellStyle()
        boldStyle.setFont(boldFont)

        // init borderStyle
        boldWithBorderStyle = workBook.createCellStyle()
        boldWithBorderStyle.setFont(boldFont)
        boldWithBorderStyle.borderBottom = BorderStyle.THICK
        boldWithBorderStyle.borderTop = BorderStyle.THICK
        boldWithBorderStyle.borderLeft = BorderStyle.THICK
        boldWithBorderStyle.borderRight = BorderStyle.THICK
    }
}