package com.example.ppm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ppm.screen.CategorySelectionScreen
import com.example.ppm.screen.PersonalDataFormScreen
import com.example.ppm.screen.PersonalDataFormScreenPreview
import com.example.ppm.ui.theme.PpmTheme
import com.example.ppm.screen.SugengEWasteHomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() 
        setContent {
            PpmTheme {
                PersonalDataFormScreen()
            }
        }
    }
}
