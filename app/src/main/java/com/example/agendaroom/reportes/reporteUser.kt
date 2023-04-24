package com.example.agendaroom.reportes

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.core.content.FileProvider
import com.example.agendaroom.R
import com.example.agendaroom.databinding.ActivityReporteUserBinding
import com.itextpdf.text.BaseColor
import com.itextpdf.text.Document
import com.itextpdf.text.Element
import com.itextpdf.text.Font
import com.itextpdf.text.PageSize
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class reporteUser : AppCompatActivity() {

    lateinit var views:ActivityReporteUserBinding
    var file=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        views= ActivityReporteUserBinding.inflate(layoutInflater)
        setContentView(views.root)

        views.apply {
            btnGenerar.setOnClickListener {
                file=generarPDF()
            }
            btnAbrir.setOnClickListener {
                openPDF(file)
            }
        }

    }

    fun generarPDF():String{

        //CONFIG FUENTE
        val font= Font(Font.FontFamily.TIMES_ROMAN,22f,Font.BOLD, BaseColor.BLUE)

        //CREANDO EL DOCUMENTO
        val document=Document(PageSize.A4.rotate(),16f,16f,16f,16f)
        val separador=Paragraph()

        try{
            val dir: File?

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                dir=applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            }else{

                dir=File(Environment.getExternalStorageDirectory().toString(),"PDF")

                if(!dir.exists())
                    dir.mkdir()

            }

            var myPDF=File(dir,"data2.pdf")

            PdfWriter.getInstance(document,FileOutputStream(myPDF))

            document.open()

            document.add(
                Paragraph("Reporte de Alumnos de LAChirana Plat: Android Developer",font).apply {
                    alignment=Element.ALIGN_CENTER
                }
            )

            separador.spacingAfter=30f
            document.add(separador)
            document.add(
                Paragraph("=".repeat(50),font).apply {
                    alignment=Element.ALIGN_CENTER
                }
            )

            document.close()
            Log.i("result","Archivo creado correctamente")
            views.btnAbrir.visibility= View.VISIBLE
            return myPDF.absolutePath

        }catch (e:Exception){
            Log.i("result",e.message.toString())
        }

        return ""
    }
    fun openPDF(ruta:String){

        val arch=File(ruta)
        val intent=Intent(Intent.ACTION_VIEW)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            intent.setDataAndType(
                FileProvider.getUriForFile(
                applicationContext,
                applicationContext.packageName+".provider",arch),"application/pdf"
                )
        }else{
            intent.setDataAndType(Uri.fromFile(arch),"application/pdf")
        }

        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        applicationContext.startActivity(intent)
    }
}