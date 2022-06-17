package com.pawlowski.costscounter.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.pawlowski.costscounter.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

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