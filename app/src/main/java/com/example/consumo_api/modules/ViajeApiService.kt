package com.example.consumo_api.modules

// Retrofit usa interfaces para definir cómo debe hacer las peticiones HTTP.

import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ViajeApiService {
    // Llama a GET /animales y devuelve una lista de animales.
    @GET("viajes")
    suspend fun getViajes(): List<Viaje>

    @GET("viajes/{id}")
    suspend fun getViajeById(@Path("id") id: Int): Viaje


    // Envía un objeto JSON al backend y lo crea en la base de datos.
    @POST("viajes")
    suspend fun crearViaje(@Body viaje: Viaje): Viaje

    // Actualiza todos los campos del objeto con PUT
    @PUT("viajes/{id}")
    @JvmSuppressWildcards
    suspend fun updateViaje(@Path("id") id: Int,@Body viaje: Viaje): Viaje

    // Solo actualiza algunos campos del animal usando PATCH.
    @PATCH("viajes/{id}")
    @JvmSuppressWildcards //resuelve conflictos en Retrofit y en Room
    suspend fun patchViaje(@Path("id") id: Int, @Body viaje: Map<String, Any>): Viaje

    @DELETE("viajes/{id}")
    suspend fun deleteViaje(@Path("id") id: Int) //Response<Unit>
}