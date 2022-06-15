package com.pawlowski.costscounter.excel_related

import android.os.Environment
import org.apache.poi.ss.usermodel.Workbook
import java.io.File
import java.io.FileOutputStream

class ExcelSaverInDocumentsImpl: ExcelSaver {
    override fun save(fileName: String, workBook: Workbook) {
        val documentsFolder = File("${Environment.getExternalStorageDirectory()}/Documents")
        val file = File(documentsFolder, "${fileName}.xlsx")
        //val appFolder = getExternalFilesDir(null)
        //Log.d("path1", file.absolutePath)
        //Log.d("path2", documentsFolder.absolutePath)
        val outputStream = FileOutputStream(file)
        workBook.write(outputStream)
        //TODO: Check does file already exists
    }

}