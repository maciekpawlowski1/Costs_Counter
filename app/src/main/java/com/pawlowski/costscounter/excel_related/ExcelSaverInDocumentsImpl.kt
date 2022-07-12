package com.pawlowski.costscounter.excel_related

import android.os.Environment
import android.util.Log
import org.apache.poi.ss.usermodel.Workbook
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ExcelSaverInDocumentsImpl @Inject constructor(): ExcelSaver {
    override fun save(fileName: String, workBook: Workbook) {
        val fileNameToSave = fileName.replace(' ', '_')
        val documentsFolder = File("${Environment.getExternalStorageDirectory()}/Documents")
        val file = File(documentsFolder, "${fileNameToSave}.xlsx")
        //val appFolder = getExternalFilesDir(null)
        //Log.d("path1", file.absolutePath)
        //Log.d("path2", documentsFolder.absolutePath)
        Log.d("saving", file.absolutePath)
        val outputStream = FileOutputStream(file)
        workBook.write(outputStream)
        //TODO: Check does file already exists
    }

}