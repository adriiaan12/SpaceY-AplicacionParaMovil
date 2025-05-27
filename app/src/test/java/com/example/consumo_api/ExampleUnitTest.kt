package com.example.consumo_api

import org.junit.Test

import org.junit.Assert.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.example.consumo_api.models.ViewModel_class
import com.example.consumo_api.ui.screens.TaskScreen
import org.junit.Rule



import com.example.consumo_api.ui.screens.TaskScreen

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Test 1: Verificar que todos los campos de texto se muestran correctamente.
     * Objetivo: Asegurarse de que los campos estén visibles para el usuario.
     */
    @Test
    fun camposTexto_visibles() {
        composeTestRule.setContent {
            TaskScreen( ViewModel_class())
        }

        composeTestRule.onNodeWithText("Nombre").assertIsDisplayed()
        composeTestRule.onNodeWithText("Apellido").assertIsDisplayed()
        composeTestRule.onNodeWithText("Correo").assertIsDisplayed()
        composeTestRule.onNodeWithText("Telefono").assertIsDisplayed()
        composeTestRule.onNodeWithText("Número de Personas").assertIsDisplayed()
    }

    /**
     * Test 2: Llenar todos los campos y enviar el formulario exitosamente.
     * Objetivo: Verificar que el flujo de envío funcione correctamente cuando los datos son válidos.
     */
    @Test
    fun envioExitoso_datosValidos() {
        val viewModel = ViewModel_class()
        viewModel.onFechainiChange("01/01/2025")
        viewModel.onFechafinChange("10/01/2025")

        composeTestRule.setContent { TaskScreen(viewModel) }

        composeTestRule.onNodeWithText("Nombre").performTextInput("Juan")
        composeTestRule.onNodeWithText("Apellido").performTextInput("Pérez")
        composeTestRule.onNodeWithText("Correo").performTextInput("juan@example.com")
        composeTestRule.onNodeWithText("Telefono").performTextInput("123456789")
        composeTestRule.onNodeWithText("Número de Personas").performTextInput("3")
        composeTestRule.onNodeWithText("Tipo de Cohete").performClick()
        composeTestRule.onNodeWithText("Falcon-9").performClick()
        composeTestRule.onNodeWithText("Tipo de Plan").performClick()
        composeTestRule.onNodeWithText("Marte").performClick()
        composeTestRule.onNodeWithText("Embarazada").performClick()
        composeTestRule.onNodeWithText("Enviar").performClick()

        assert(viewModel.uiState.value.envioExitoso)
    }

    /**
     * Test 3: Verificar error si no se ingresa el nombre.
     * Objetivo: Comprobar la validación de campo obligatorio.
     */
    @Test
    fun errorSiNombreVacio() {
        val viewModel = ViewModel_class()
        viewModel.onFechainiChange("01/01/2025")
        viewModel.onFechafinChange("10/01/2025")

        composeTestRule.setContent { TaskScreen(viewModel) }

        composeTestRule.onNodeWithText("Enviar").performClick()
        composeTestRule.onNodeWithText("El nombre no puede estar vacío").assertIsDisplayed()
    }

    /**
     * Test 4: Verificar selección correcta de fecha desde el ViewModel.
     * Objetivo: Confirmar que la fecha seleccionada se refleja correctamente en el campo.
     */
    @Test
    fun seleccionFecha_inicio_seMuestraCorrectamente() {
        val viewModel = ViewModel_class()
        viewModel.onFechainiChange("02/02/2025")

        composeTestRule.setContent { TaskScreen(viewModel) }

        composeTestRule.onNodeWithText("Fecha de Inicio Viaje").assertTextContains("02/02/2025")
    }

    /**
     * Test 5: Verificar que se puede marcar el checkbox "Embarazada".
     * Objetivo: Asegurar que el checkbox responde correctamente a la interacción.
     */
    @Test
    fun marcarCheckboxEmbarazada() {
        composeTestRule.setContent { TaskScreen(ViewModel_class()) }
        composeTestRule.onNodeWithText("Embarazada").performClick()
        composeTestRule.onNodeWithText("Embarazada").assertIsSelected()
    }
}