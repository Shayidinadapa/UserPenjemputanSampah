package com.example.ppm.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SugengEWasteHomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF00C853))
                    .padding(16.dp)
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
                .background(Color(0xFF00C853))
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoCard(title = "Poin saya", icon = "💰", value = "15", modifier = Modifier.weight(1f)) // Tambah weight
            Spacer(modifier = Modifier.width(8.dp))
            InfoCard(title = "Total Sampah", icon = "🗑", value = "50", modifier = Modifier.weight(1f)) // Tambah weight
        }


        // Donasi Poin
        Text("Donasi Poin", modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold) // Tambah vertical padding & bold
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFF00C853))
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
    Card(modifier = modifier
        .padding(4.dp)
        .fillMaxWidth()) {
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
            .background(Color(0xFF00C853))
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