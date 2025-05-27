package com.example.consumo_api.ui.screens

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import android.graphics.BitmapFactory
import android.graphics.Bitmap

fun generarPdf(context: Context, salas: List<Sala>) {
    val pdfDocument = PdfDocument()

    val titlePaint = android.graphics.Paint().apply {
        textSize = 24f
        isFakeBoldText = true
        color = android.graphics.Color.rgb(33, 150, 243) // azul intenso
    }

    val subtitlePaint = android.graphics.Paint().apply {
        textSize = 18f
        isFakeBoldText = true
        color = android.graphics.Color.rgb(100, 100, 100) // gris oscuro
    }

    val contentPaint = android.graphics.Paint().apply {
        textSize = 14f
        color = android.graphics.Color.BLACK
    }

    val linePaint = android.graphics.Paint().apply {
        color = android.graphics.Color.LTGRAY
        strokeWidth = 2f
    }

    val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
    var page = pdfDocument.startPage(pageInfo)
    val canvas = page.canvas

    var y = 60
    val marginLeft = 40

    salas.forEachIndexed { index, sala ->
        // Título
        //canvas.drawText(sala.titulo, marginLeft.toFloat(), y.toFloat(), titlePaint)
        y += 35

        // Imagen
        val bitmap = BitmapFactory.decodeResource(context.resources, sala.imagenRes)
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 250, 150, false)
        canvas.drawBitmap(scaledBitmap, marginLeft.toFloat(), y.toFloat(), null)
        y += scaledBitmap.height + 20

        // Descripción
        canvas.drawText("Descripción:", marginLeft.toFloat(), y.toFloat(), subtitlePaint)
        y += 22
        canvas.drawText(sala.descripcion, marginLeft.toFloat(), y.toFloat(), contentPaint)
        y += 20
        canvas.drawText(sala.descripcion2, marginLeft.toFloat(), y.toFloat(), contentPaint)
        y += 30

        // Línea divisoria
        canvas.drawLine(marginLeft.toFloat(), y.toFloat(), (pageInfo.pageWidth - marginLeft).toFloat(), y.toFloat(), linePaint)
        y += 15

        // Servicios
        canvas.drawText("Servicios e instalaciones:", marginLeft.toFloat(), y.toFloat(), subtitlePaint)
        y += 22

        sala.tablaInfo.forEach { (servicio, descripcion) ->
            canvas.drawText("- $servicio: $descripcion", marginLeft.toFloat() + 20, y.toFloat(), contentPaint)
            y += 18
        }

        y += 40

        // Si no cabe en la página, crea una nueva página
        if (y > pageInfo.pageHeight - 100 && index != salas.lastIndex) {
            pdfDocument.finishPage(page)
            page = pdfDocument.startPage(pageInfo)
            y = 60
        }
    }

    pdfDocument.finishPage(page)

    val directory = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
    val file = File(directory, "informacion_salas.pdf")

    try {
        FileOutputStream(file).use {
            pdfDocument.writeTo(it)
        }

        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/pdf")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        context.startActivity(Intent.createChooser(intent, "Abrir PDF con..."))

    } catch (e: Exception) {
        e.printStackTrace()
    }

    pdfDocument.close()
}

private fun abrirPdf(context: Context, file: File) {
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )

    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, "application/pdf")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "No se pudo abrir el PDF", Toast.LENGTH_LONG).show()
        }
}