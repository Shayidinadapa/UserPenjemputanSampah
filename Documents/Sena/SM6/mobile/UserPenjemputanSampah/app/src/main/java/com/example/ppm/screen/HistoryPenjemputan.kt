package com.example.ppm.screen

import com.example.ppm.ui.theme.PpmTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.ppm.navigation.Screen
import com.example.ppm.ui.components.BottomBar

data class PenjemputanHistory(
    val location: String,
    val details: String,
    val date: String // Bisa "Baru saja" atau tanggal
)

@Composable
fun HistoryPenjemputanScreen(modifier: Modifier = Modifier) {
    val historyItems = listOf(
        PenjemputanHistory("Borma - Universitas Pasundan", "Handphone 10, Laptop 12, Pc ....", "Baru saja"),
        PenjemputanHistory("Gg. Haji Ridho - Unpas", "Laptop 11, Setrika 1, Bola lam ....", "10/10/2024"),
        PenjemputanHistory("Jl. Nyengseret - Unpas", "Setrika, Baterai 1, Laptop 10 ....", "12/08/2024")
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp), // Cukup horizontal padding, vertical diatur oleh spacedBy
        contentPadding = PaddingValues(vertical = 8.dp), // Gunakan contentPadding untuk jarak atas & bawah
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(historyItems) { item ->
            // FIX 1: Menambahkan parameter onClick yang hilang.
            // Karena belum ada aksi, kita beri lambda kosong {}
            HistoryItemCard(item = item, onClick = {})
        }
    }
}

@Composable
fun HistoryItemCard(item: PenjemputanHistory, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick), // Modifier clickable sebaiknya di paling atas
        shape = RoundedCornerShape(8.dp), // Gunakan shape, bukan clip
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        // FIX 2: Cara memberi warna Card di Material 3 yang benar
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF28B446) // Warna hijau untuk container-nya
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Icon(
                    imageVector = Icons.Default.Delete, // Ikon tempat sampah
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = item.location,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = item.details,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp)) // Beri jarak agar tidak terlalu mepet
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = item.date,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp)) // Beri jarak antara tanggal dan ikon
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Detail",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HistoryPenjemputanWithScaffoldPreview() {
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
            HistoryPenjemputanScreen(modifier = Modifier.padding(paddingValues))
        }
    }
}