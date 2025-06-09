package com.example.ppm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ppm.ui.theme.PpmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PpmTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar()
                    }
                ) { padding ->
                    SugengEWasteHomeScreen(modifier = Modifier.padding(padding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier) {
    Text(text = "Hello $name!")
}

@Composable
fun SugengEWasteHomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header Pengumuman
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF00C853))
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.background(Color(0x00000000))) {
                Text("Pengumuman!", color = Color.White, fontSize = 18.sp)
                Text(
                    "Dapatkan poin tambahan dengan memposting foto kegiatan penjemputan sampah di sosial media",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }

        // Poin dan Total Sampah
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoCard(title = "Poin saya", icon = "💰", value = "15")
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoCard(title = "Total Sampah", icon = "🗑", value = "50")
        }

        // Donasi Poin
        Text("Donasi Poin", modifier = Modifier.padding(horizontal = 16.dp), fontSize = 18.sp)
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            DonasiCard("Penumpukan sampah elektronik yang semakin banyak")
            DonasiCard("Donasi Untuk Yatim Piatu")
        }

        // Ajak Teman
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Ajak Temanmu!!", fontSize = 16.sp)
                    Text("Pakai Sugeng E-Waste", fontSize = 14.sp)
                }
                Button(onClick = { /* Share action */ }) {
                    Text("Share")
                }
            }
        }
    }
}

@Composable
fun InfoCard(title: String, icon: String, value: String) {
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(title)
            Text("$icon $value", fontSize = 16.sp)
        }
    }
}

@Composable
fun DonasiCard(title: String) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(modifier = Modifier.height(80.dp).fillMaxWidth().background(Color.Gray)) {
                // Bisa isi dengan Image
            }
            Text(title, fontSize = 12.sp)
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar {
        NavigationBarItem(icon = { Icon(Icons.Default.Home, null) }, label = { Text("Beranda") }, selected = true, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.List, null) }, label = { Text("Activity") }, selected = false, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.ShoppingCart, null) }, label = { Text("Jemput") }, selected = false, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.Place, null) }, label = { Text("Tracking") }, selected = false, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.Person, null) }, label = { Text("Profil") }, selected = false, onClick = {})
    }
}