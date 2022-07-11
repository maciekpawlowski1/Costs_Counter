package com.pawlowski.costscounter.presentation.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pawlowski.costscounter.R
import com.pawlowski.costscounter.data.entities.ReportEntity
import com.pawlowski.costscounter.presentation.report_details.ReportDetailsActivity
import com.pawlowski.costscounter.presentation.report_details.dialogs.DialogWithOneEditText
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReportsActivity : AppCompatActivity(), ReportsAdapter.CardItemClickListeners,
    DialogWithOneEditText.OnDialogButtonsClickListener {

    private val viewModel: ReportsActivityViewModel by viewModels()

    private lateinit var recycler: RecyclerView
    private val adapter = ReportsAdapter(this)
    private lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        addButton = findViewById(R.id.add_new_report_report_activity)
        recycler = findViewById(R.id.cost_report_recycler_report_activity)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        viewModel.reports.observe(this)
        {
            adapter.reports = it
        }

        addButton.setOnClickListener(this::onAddClickListener)


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

    private fun onAddClickListener(view: View)
    {
        val dialog = DialogWithOneEditText(this, dialogTittle = "Add new report", inputHint = "Report name")
        dialog.show(supportFragmentManager, "AddReportDialog")
    }

    override fun onDeleteClick(reportEntity: ReportEntity) {
        viewModel.deleteReport(reportEntity)
    }

    override fun onCardClick(reportEntity: ReportEntity) {
        ReportDetailsActivity.launch(this, reportEntity.reportId)
    }

    override fun onConfirmButtonClickInDialog(name: String) {
        viewModel.insertNewReport(name)
    }

}