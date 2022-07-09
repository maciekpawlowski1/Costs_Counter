package com.pawlowski.costscounter.presentation.report_details

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pawlowski.costscounter.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReportDetailsActivity: AppCompatActivity() {

    private val viewModel by viewModels<ReportDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_details)

        viewModel.report.observe(this)
        {
            Log.d("tittle", it.reportEntity.reportName)
        }
    }
}