package com.example.prcticaapi_list.navigation

sealed class Routes(val route: String) {
    object Pantalla1: Routes("LaunchScreen")
    object Pantalla2: Routes("ListScreen")
    object Pantalla3: Routes("DetailScreen/{displayName}")
    fun createRoute(displayName: String): String {
        return "DetailScreen/$displayName"
    }
    object Pantalla4: Routes("FavoriteScreen")
}
