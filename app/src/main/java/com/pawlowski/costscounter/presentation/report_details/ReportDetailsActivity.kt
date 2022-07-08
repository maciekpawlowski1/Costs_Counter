package com.pawlowski.costscounter.presentation.report_details

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.pawlowski.costscounter.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReportDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_report_details)
    }
}