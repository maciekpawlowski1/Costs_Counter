package com.pawlowski.costscounter.excel_related

import android.util.Log
import com.pawlowski.costscounter.data.entities.ReportWithItemsAndCategories
import com.pawlowski.costscounter.models.CostItem
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import javax.inject.Inject

class ExcelGeneratorFromReportClass @Inject constructor(
    private val excelSaver: ExcelSaver
) {

    fun createExcelFromReportEntity(reportEntity: ReportWithItemsAndCategories)
    {
        val workBook = XSSFWorkbook()
        val sheetsGenerator: ExcelSheetsGenerator = ExcelSheetsGeneratorImpl(workBook, ExcelStylesUtil(workBook))
        val itemsToAdd = mutableListOf<CostItem>()
        itemsToAdd.addAll(reportEntity.categoryItems.filter { it.items.isNotEmpty() }.map { CostItem(it.categoryEntity.categoryId, it.categoryEntity.categoryName, it.items.sumOf { it.cost*it.amount }, 1) })
        itemsToAdd.addAll(reportEntity.costItems.map { CostItem(it.itemId, it.name, it.cost, it.amount) })

        sheetsGenerator.addSheetWithCostData(itemsToAdd, "Main")

        val categoryNames = mutableMapOf<String, Int>()

        for (el in reportEntity.categoryItems)
        {
            if(el.items.isEmpty())
                continue
            var name = el.categoryEntity.categoryName
            if(categoryNames.containsKey(name))
            {
                val prevName = name
                name += "(${categoryNames[name]})"
                categoryNames[prevName] = categoryNames[prevName]!! + 1
            }
            else
            {
                categoryNames[name] = 1
            }
            sheetsGenerator.addSheetWithCostData(el.items.map { CostItem(it.categoryItemId, it.name, it.cost, it.amount) }, name)
        }

        excelSaver.save(reportEntity.reportEntity.reportName, workBook)
        Log.d("saving", "saved")
    }
}