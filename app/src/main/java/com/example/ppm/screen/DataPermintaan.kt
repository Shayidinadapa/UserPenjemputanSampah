package com.example.ppm.screen

import com.example.ppm.ui.theme.PpmTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataPermintaanScreen(navController: NavController? = null) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Data Permintaan",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween // Agar tombol di bawah
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                PermintaanDetailRow(label = "Nama", value = "Genta Sena")
                PermintaanDetailRow(label = "Alamat", value = "Bandung")
                PermintaanDetailRow(label = "No Telepon", value = "08888888")
                PermintaanDetailRow(label = "Lokasi Penjemputan", value = "Setiabudhi")
                PermintaanDetailRow(label = "Total Sampah", value = "50")
                PermintaanDetailRow(label = "Total Poin", value = "50")
            }

            // Tombol Konfirmasi
            Button(
                onClick = {
                    println("Permintaan dikonfirmasi!")
//                    navController?.popBackStack(NavRoutes.HOME, inclusive = false)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF28B446)), // Warna hijau
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Konfirmasi", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun PermintaanDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
    }
    Divider(color = Color.LightGray, thickness = 1.dp) // Garis pemisah
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun DataPermintaanScreenPreview() {
    PpmTheme {
        DataPermintaanScreen()
    }
}