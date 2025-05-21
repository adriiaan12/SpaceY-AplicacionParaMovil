package com.example.consumo_api.models

//Se importan las herramientas necesarias para usar ViewModel (de arquitectura de Jetpack) y
// StateFlow (de Kotlin Coroutines) para el manejo reactivo del estado, es decir, que la app reaccione automáticamente a los cambios que haya en la UI


data class ViewModel_form(
    val nombre: String = "",
    val apellido: String = "",
    val correo: String = "",
    val tlf: String = "",
    val npersonas: Int = 0,
    val tipocohete: String = "",
    val plan: String = "",
    val fechaini: String = "",
    val fechafin: String = "",
    val embarazada: Boolean = false,
    val errorMensaje: String? = null,
    val envioExitoso: Boolean = false
)



