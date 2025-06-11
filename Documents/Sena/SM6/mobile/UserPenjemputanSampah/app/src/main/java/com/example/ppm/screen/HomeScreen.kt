package com.example.ppm.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.ppm.navigation.Screen
import com.example.ppm.ui.components.BottomBar
import com.example.ppm.ui.theme.PpmTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF00C853))
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF00C853))
                    .padding(8.dp)
            ) {
                Text("Pengumuman!", color = Color.White, fontSize = 18.sp)
                Text(
                    "Dapatkan poin tambahan dengan memposting foto kegiatan penjemputan sampah di sosial media",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoCard(title = "Poin saya", icon = "💰", value = "15", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            InfoCard(title = "Total Sampah", icon = "🗑", value = "50", modifier = Modifier.weight(1f))
        }


        // Donasi Poin
        Text("Donasi Poin", modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold) // Tambah vertical padding & bold
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DonasiCard("Penumpukan sampah elektronik yang semakin banyak", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            DonasiCard("Donasi Untuk Yatim Piatu", modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF00C853))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Ajak Temanmu!!", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text("Pakai Sugeng E-Waste", fontSize = 14.sp)
                }
                Button(onClick = { /* Share action */ }) {
                    Text("Share")
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))


    }
}

@Composable
fun InfoCard(title: String, icon: String, value: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(title, fontWeight = FontWeight.SemiBold)
            Text("$icon $value", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun DonasiCard(title: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(Color(0xFF00C853))
                .clip(RoundedCornerShape(4.dp))
            ) {
            }
            Spacer(modifier = Modifier.height(8.dp)) // Jarak antara Box dan Text
            Text(title, fontSize = 12.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenWithScaffoldPreview() {
    PpmTheme {
        Scaffold(
            bottomBar = {
                // Beri data dummy ke BottomBar
                BottomBar(
                    currentRoute = Screen.Home.route, // Anggap saja sedang di halaman Home
                    onItemClick = { } // Tidak perlu melakukan apa-apa saat diklik di preview
                )
            }
        ) { paddingValues ->
            HomeScreen(modifier = Modifier.padding(paddingValues))
        }
    }
}