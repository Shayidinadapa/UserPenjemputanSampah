//package com.example.ppm.screen
//
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.List
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.Place
//import androidx.compose.material.icons.filled.ShoppingCart
//import androidx.compose.material3.Icon
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.graphics.Color
//import androidx.navigation.NavController
////import com.example.ppm.NavRoutes
//
//@Composable
//fun BottomNavigationBar(navController: NavController, currentRoute: String?) {
//    NavigationBar(
//        containerColor = Color.White // Set background putih untuk NavigationBar
//    ) {
//        NavigationBarItem(
//            icon = { Icon(Icons.Default.Home, null) },
//            label = { Text("Beranda") },
//            selected = currentRoute == NavRoutes.HOME,
//            onClick = {
//                navController.navigate(NavRoutes.HOME) {
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }
//        )
//        NavigationBarItem(
//            icon = { Icon(Icons.Default.List, null) },
//            label = { Text("Activity") },
//            selected = currentRoute == NavRoutes.CATEGORY_SELECTION, // Selected jika di rute kategori
//            onClick = {
//                navController.navigate(NavRoutes.CATEGORY_SELECTION) { // Navigasi ke rute kategori
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }
//        )
//        NavigationBarItem(
//            icon = { Icon(Icons.Default.ShoppingCart, null) },
//            label = { Text("Jemput") },
//            selected = currentRoute == NavRoutes.JEMPUT,
//            onClick = {
//                navController.navigate(NavRoutes.JEMPUT) {
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }
//        )
//        NavigationBarItem(
//            icon = { Icon(Icons.Default.Place, null) },
//            label = { Text("Tracking") },
//            selected = currentRoute == NavRoutes.TRACKING,
//            onClick = {
//                navController.navigate(NavRoutes.TRACKING) {
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }
//        )
//        NavigationBarItem(
//            icon = { Icon(Icons.Default.Person, null) },
//            label = { Text("Profil") },
//            selected = currentRoute == NavRoutes.PROFILE,
//            onClick = {
//                navController.navigate(NavRoutes.PROFILE) {
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }
//        )
//    }
//}