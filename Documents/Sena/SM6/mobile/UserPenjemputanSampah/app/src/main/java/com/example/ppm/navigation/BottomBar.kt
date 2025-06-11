// Taruh ini di file terpisah atau di bawah Composable MainScreen Anda, misal: ui/components/MyBottomBar.kt
package com.example.ppm.ui.components // Contoh package

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ppm.navigation.Screen

// ui/components/BottomBar.kt

@Composable
fun BottomBar(
    currentRoute: String?, // Rute yang sedang aktif
    onItemClick: (String) -> Unit // Aksi saat item diklik, mengirimkan rute tujuan
) {
    val navItems = listOf(
        Screen.Activitas,
        Screen.Home,
        Screen.Jemput,
        Screen.Lokasi,
        Screen.Profile
    )

    NavigationBar {
        navItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route, // Gunakan currentRoute untuk perbandingan
                onClick = { onItemClick(item.route) }, // Panggil lambda onItemClick
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(text = item.label) }
            )
        }
    }
}