package com.example.prcticaapi_list.navigation

sealed class Routes(val route: String) {
    object Pantalla1: Routes("LaunchScreen")
    object Pantalla2: Routes("ListScreen")
}
