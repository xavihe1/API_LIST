package com.example.prcticaapi_list.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreens (val route: String, val icon: ImageVector, val label: String) {
    object Home: BottomNavigationScreens(Routes.Pantalla2.route, Icons.Filled.Home, "Home")
    object Favorite: BottomNavigationScreens(Routes.Pantalla4.route, Icons.Filled.Favorite, "Favoritos")
}
