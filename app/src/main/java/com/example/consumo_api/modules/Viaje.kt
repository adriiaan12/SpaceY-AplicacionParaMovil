package com.example.consumo_api.modules

// Este data class representa el objeto que usarás en la app y que corresponde a lo que la API retorna.

data class Viaje(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val tlf: String,
    val npersonas: Int,
    val tipocohete: String,
    val plan: String,
    val fechaini: String,
    val fechafin: String,
    val embarazada: Boolean
)

// Equivale a cada entrada de la API animales que tenías en Node.js.