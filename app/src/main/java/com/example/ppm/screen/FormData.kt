package com.example.ppm.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ppm.ui.theme.PpmTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDataFormScreen(navController: NavController? = null) {
    var nama by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var noTelepon by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Form Data diri",
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nama
            FormField(
                label = "Nama",
                placeholder = "Masukkan Nama",
                value = nama,
                onValueChange = { nama = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Alamat
            FormField(
                label = "Alamat",
                placeholder = "Masukkan Alamat",
                value = alamat,
                onValueChange = { alamat = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Lokasi",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color.LightGray)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Text("Placeholder Peta/Lokasi", color = Color.DarkGray)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // No Telepon
            FormField(
                label = "No Telepon",
                placeholder = "Masukkan No Telepon",
                value = noTelepon,
                onValueChange = { noTelepon = it },
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(32.dp))


            Button(
                onClick = {

                    println("Nama: $nama")
                    println("Alamat: $alamat")
                    println("No Telepon: $noTelepon")

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF28B446)),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Kirim", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun FormField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF28B446),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF28B446),
                unfocusedLabelColor = Color.Gray,
                cursorColor = Color(0xFF28B446)
            )
        )
    }
}


@Preview(showBackground = true, widthDp = 360)
@Composable
fun PersonalDataFormScreenPreview() {
    PpmTheme {
        PersonalDataFormScreen()
    }
}