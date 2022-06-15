package com.pawlowski.costscounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pawlowski.costscounter.excel_related.*
import dagger.hilt.android.AndroidEntryPoint
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val workBook: Workbook = XSSFWorkbook()
//        val sheetsGenerator: ExcelSheetsGenerator = ExcelSheetsGeneratorImpl(workBook, ExcelStylesUtil(workBook))
//        val items = listOf(CostItem("szafa", 15.10, 1),
//            CostItem("biurko", 10.99, 1),
//            CostItem("dlugopisy", 13.00, 1),
//            CostItem("woda", 20.17, 1)
//            )
//        sheetsGenerator.addSheetWithCostData(items, "wydatki")
//
//        val excelSaver: ExcelSaver = ExcelSaverInDocumentsImpl()
//        excelSaver.save("wydatki", workBook)
    }
}