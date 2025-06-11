package com.example.ppm.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Activitas : Screen("aktivitas", "Aktivitas", Icons.Default.Checklist)
    object Home : Screen("beranda", "Beranda", Icons.Default.Home)
    object Jemput : Screen("jemput", "Jemput", Icons.Default.ShoppingCart)
    object Lokasi : Screen("lokasi", "Lokasi", Icons.Default.LocationOn)
    object Profile : Screen("profile", "Profil", Icons.Default.Person)
}