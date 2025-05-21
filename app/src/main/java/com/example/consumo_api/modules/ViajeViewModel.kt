package com.example.consumo_api.modules

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViajeViewModel : ViewModel() {

    //Mantiene el estado de la lista de animales

    var message by mutableStateOf("")
    private val api = RetrofitClient.api

    private val _viajes = MutableStateFlow<List<Viaje>>(emptyList())
    var viajeDetail by mutableStateOf<Viaje?>(null)
        private set


    val viajes: StateFlow<List<Viaje>> = _viajes


    fun obtenerViajes() {
        viewModelScope.launch {
            try {
                _viajes.value = api.getViajes()
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Error al OBTENER animales", e)
            }
        }
    }

    fun buscarViajePorId(id: Int) {
        viewModelScope.launch {
            try {
                viajeDetail = api.getViajeById(id)
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Error al OBTENER animal por ID", e)
                viajeDetail = null
            }
        }
    }
    //POST
    fun agregarViaje(viaje: Viaje) {
        viewModelScope.launch {
            try {
                val created = api.crearViaje(viaje)
                message = "Creado: ${created.nombre}"
                obtenerViajes()
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Error al AGREGAR animal", e)
            }
        }
    }

    // PUT
    fun editarViaje(id: Int, viaje: Viaje) {
        viewModelScope.launch {
            try {
                val mod = api.updateViaje(id, viaje)
                message = "Editado completamente: ${mod.id}"
                obtenerViajes()
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Error al EDITAR (PUT) animal", e)
            }
        }
    }
    // PATCH
    fun patchViaje(id: Int, fields: Map<String, Any>) {
        viewModelScope.launch {
            try {
                val patched = api.patchViaje(id, fields)
                message = "Editado parcialmente: ${patched.id}"
                obtenerViajes()
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Error al EDITAR PARCIALMENTE (PATCH) animal", e)
            }

        }
    }
    // DELETE
    fun eliminarViaje(id: Int) {
        viewModelScope.launch {
            try {
                api.deleteViaje(id)
                message = "Eliminado ID: $id"
                obtenerViajes()
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Error al ELIMINAR animal", e)
            }
        }
    }
}