package com.example.consumo_api.ui.components

import android.R.attr.label
import android.graphics.Color
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import com.example.consumo_api.ui.theme.Pink80
import androidx.compose.runtime.*
import com.example.consumo_api.ui.theme.White
import com.example.consumo_api.ui.theme.Purple40
import com.example.consumo_api.ui.theme.Black
import com.example.consumo_api.ui.theme.Yellow40


@Composable
fun AppDrawer(onDestinationClicked: (String) -> Unit) {




    ModalDrawerSheet {
        ModalDrawerSheet(
            drawerContainerColor =  Black// ✅ AQUÍ sí está permitido
        ) {
            Text(
                "Menú",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp),
                color = White
            )

            NavigationDrawerItem(
                label = { Text("Inicio") },
                selected = false,
                onClick = { onDestinationClicked("home") },
                icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                    unselectedContainerColor = Black,
                    selectedIconColor = MaterialTheme.colorScheme.contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary),
                    unselectedIconColor = MaterialTheme.colorScheme.contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary),
                    selectedTextColor = White,
                    unselectedTextColor = White
                )
            )
            NavigationDrawerItem(
                label = { Text("Reservas") },
                selected = false,
                onClick = { onDestinationClicked("taskIntro") },
                icon = { Icon(Icons.Default.DateRange, contentDescription = "Reservas") },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                    unselectedContainerColor = Black,
                    selectedIconColor = MaterialTheme.colorScheme.contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary),
                    // Dame el color de contenido (texto/ícono) que combine con primary”.
                    unselectedIconColor = MaterialTheme.colorScheme.contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary),
                    selectedTextColor = White,
                    unselectedTextColor = White
                )
            )



            NavigationDrawerItem(
                label = { Text("Perfil") },
                selected = false,

                onClick = {

                    onDestinationClicked("profile")

                },
                icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Pink80, //MaterialTheme.colorScheme.primary.copy(alpha = 0.25f),
                    unselectedContainerColor = Black,  //MaterialTheme.colorScheme.surface,
                    selectedIconColor = MaterialTheme.colorScheme.contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary),
                    // Dame el color de contenido (texto/ícono) que combine con primary”.
                    unselectedIconColor = MaterialTheme.colorScheme.contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary),
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = White
                )
            )

            NavigationDrawerItem(
                label = { Text("Salas") },
                selected = false,
                onClick = { onDestinationClicked("Sala") },
                icon = { Icon(Icons.Default.Info, contentDescription = "Salas") },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Pink80, //MaterialTheme.colorScheme.primary.copy(alpha = 0.25f),
                    unselectedContainerColor = Black,  //MaterialTheme.colorScheme.surface,
                    selectedIconColor = MaterialTheme.colorScheme.contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary),
                    // Dame el color de contenido (texto/ícono) que combine con primary”.
                    unselectedIconColor = MaterialTheme.colorScheme.contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary),
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = White
                )
            )

            NavigationDrawerItem(
                label = { Text("Ubicacion") },
                selected = false,
                onClick = { onDestinationClicked("Localizacion") },
                icon = { Icon(Icons.Default.LocationOn, contentDescription = "Ubicacion") },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Pink80, //MaterialTheme.colorScheme.primary.copy(alpha = 0.25f),
                    unselectedContainerColor = Black,  //MaterialTheme.colorScheme.surface,
                    selectedIconColor = MaterialTheme.colorScheme.contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary),
                    // Dame el color de contenido (texto/ícono) que combine con primary”.
                    unselectedIconColor = MaterialTheme.colorScheme.contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary),
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = White
                )
            )
        }
    }
}