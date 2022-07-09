package com.pawlowski.costscounter.presentation.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pawlowski.costscounter.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReportsActivity : AppCompatActivity() {

    private val viewModel: ReportsActivityViewModel by viewModels()

    private lateinit var recycler: RecyclerView
    private val adapter = ReportsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        recycler = findViewById(R.id.cost_report_recycler_report_activity)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        viewModel.reports.observe(this)
        {
            adapter.reports = it
        }

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