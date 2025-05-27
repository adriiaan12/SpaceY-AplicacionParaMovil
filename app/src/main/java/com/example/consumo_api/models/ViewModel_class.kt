package com.example.consumo_api.models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import com.example.consumo_api.modules.RetrofitClient
import com.example.consumo_api.modules.Viaje
import com.example.consumo_api.modules.ViajeViewModel


//Esta clase extiende ViewModel, lo que permite que sobreviva a cambios de configuración
// como rotaciones de pantalla.

class ViewModel_class:ViewModel() {

    // _uiState es una fuente mutable del estado.

    //   uiState es la versión inmutable expuesta a la UI para observar cambios de estado.

    //  StateFlow es un flujo de datos observable que mantiene un estado actual.


    var message by mutableStateOf("")
    private val api = RetrofitClient.api

    private val _viajes = MutableStateFlow<List<Viaje>>(emptyList())
    var viajeDetail by mutableStateOf<Viaje?>(null)
        private set


    val viajes: StateFlow<List<Viaje>> = _viajes

    private val _uiState = MutableStateFlow(ViewModel_form())
    val uiState: StateFlow<ViewModel_form> = _uiState

  //  Usa update para modificar el valor actual del estado.

   // it.copy(...) crea una copia del estado anterior, con el nuevo valor actualizado.
  //  Este patrón se repite para apellido1, apellido2, dni, y email.

    fun onNombreChange(nuevoNombre: String) {
        _uiState.update { it.copy(nombre = nuevoNombre) }
    }

    fun onApellidoChange(nuevoApellido: String) {
        _uiState.update { it.copy(apellido = nuevoApellido) }
    }

    fun onCorreoChange(nuevoCorreo: String) {
        _uiState.update { it.copy(correo = nuevoCorreo) }
    }

    fun ontlfChange(nuevotlf: String) {
        _uiState.update { it.copy(tlf = nuevotlf) }
    }

    fun onnpersonasChange(nuevoNpersonas: Int) {
        _uiState.update { it.copy(npersonas = nuevoNpersonas) }
    }

    fun onFechainiChange(nuevaFecha: String) {
        _uiState.update { it.copy(fechaini = nuevaFecha) }
    }

    fun onFechafinChange(nuevaFechafin: String) {
        _uiState.update { it.copy(fechafin = nuevaFechafin) }
    }

    fun onRolChange(nuevoRol: String) {
        _uiState.update { it.copy(tipocohete = nuevoRol) }
    }

    fun onPlanChange(nuevoPlan: String) {
        _uiState.update { it.copy(plan = nuevoPlan) }
    }

    fun onEmbarazadaChange(nuevoEmbarazada: Boolean) {
        _uiState.update { it.copy(embarazada = nuevoEmbarazada) }
    }



    /*
    fun validarYEnviar() {
        val state = _uiState.value
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

        when {
            state.nombre.isBlank() -> setError("El nombre es obligatorio")
            state.apellido1.isBlank() -> setError("El primer apellido es obligatorio")
            state.tlf.length < 9 -> setError("El tlf debe tener al menos 9 caracteres")
            !emailRegex.matches(state.email) -> setError("El email no tiene un formato válido")
            else -> {
                _uiState.update {
                    it.copy(errorMensaje = null, envioExitoso = true)
                }
            }
        }
    }*/

    fun obtenerViajes() {
        viewModelScope.launch {
            try {
                _viajes.value = api.getViajes()
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Error al OBTENER animales", e)
            }
        }
    }

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

    fun validarYEnviar() {
        val state = _uiState.value
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

        when {
            state.nombre.isBlank() -> setError("El nombre es obligatorio")
            state.apellido.isBlank() -> setError("El apellido es obligatorio")
            !emailRegex.matches(state.correo) -> setError("El email no tiene un formato válido")
            state.tlf.isBlank() -> setError("El teléfono no puede estar vacío")
            state.tlf.length > 9 -> setError("El teléfono no puede tener mas de 9 números")
            state.npersonas < 1 -> setError("Tiene que haber al menos una persona")
            state.fechaini.isBlank() -> setError("La fecha de inicio no puede estar vacía")
            state.fechafin.isBlank() -> setError("La fecha de fin no puede estar vacía")
            state.fechaini>state.fechafin -> setError("La fecha de fin no puede ser menor que la fecha de inicio")
            state.tipocohete.isBlank() -> setError("Debes elegir un cohete")
            state.plan.isBlank() -> setError("Debes elegir un plan de viaje")

            else -> {
                _uiState.update {
                    it.copy(errorMensaje = null, envioExitoso = true)
                }
            }
        }
    }

    private fun setError(mensaje: String) {
        _uiState.update { it.copy(errorMensaje = mensaje, envioExitoso = false) }
    }

}

// Tenemos una UI reactiva: cuando el usuario escribe algo,
// la función correspondiente se llama y actualiza el estado.

// Gracias a StateFlow, la UI que observe uiState se volverá a componer
// automáticamente con los nuevos datos.