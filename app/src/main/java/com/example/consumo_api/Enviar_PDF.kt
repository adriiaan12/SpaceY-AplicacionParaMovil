package com.example.consumo_api

import android.R.attr.textSize
import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.widget.Toast


import com.example.consumo_api.modules.Animal
import com.example.consumo_api.modules.Viaje

import java.io.File
import java.io.FileOutputStream

fun generarPdf(context: Context, viajes: List<Viaje>) {
    val document = PdfDocument()
    val pageWidth = 300
    val pageHeight = 600
    var y = 25
    var pageNumber = 1

    val paint = Paint().apply {
        textSize = 12f
    }

    var pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create()
    var page = document.startPage(pageInfo)
    var canvas = page.canvas

    viajes.forEach { viaje ->
        val lines = listOf(
            "Id: ${viaje.id}",
            "Nombre: ${viaje.nombre}",
            "Apellido: ${viaje.apellido}",
            "Telefono: ${viaje.tlf}",
            "Correo: ${viaje.correo}",
            "Fecha Inicio: ${viaje.fechaini}",
            "Fecha Fin: ${viaje.fechafin}",
            "Plan: ${viaje.plan}",
            "Tipo de Cohete: ${viaje.tipocohete}",
            "Numero de Personas: ${viaje.npersonas}",
            "Embarazada: ${if (viaje.embarazada) "Sí" else "No"}"
        )

        for (line in lines) {
            if (y > pageHeight - 40) { // Si se pasa del límite vertical, crear nueva página
                document.finishPage(page)
                pageNumber++
                pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create()
                page = document.startPage(pageInfo)
                canvas = page.canvas
                y = 25
            }
            canvas.drawText(line, 10f, y.toFloat(), paint)
            y += 20
        }

        y += 20 // Espacio adicional entre viajes
    }

    document.finishPage(page)

    val file = File(context.getExternalFilesDir(null), "viajes.pdf")
    document.writeTo(FileOutputStream(file))
    document.close()

    Toast.makeText(context, "PDF guardado en: ${file.absolutePath}", Toast.LENGTH_LONG).show()
}
