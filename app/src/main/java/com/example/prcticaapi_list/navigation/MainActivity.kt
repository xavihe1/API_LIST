package com.example.prcticaapi_list.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.prcticaapi_list.ui.theme.PràcticaAPI_LISTTheme
import com.example.prcticaapi_list.view.DetailScreen
import com.example.prcticaapi_list.view.FavoriteScreen
import com.example.prcticaapi_list.view.LaunchScreen
import com.example.prcticaapi_list.view.ListScreen
import com.example.prcticaapi_list.viewModel.APIViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myViewModel by viewModels<APIViewModel>()
        setContent {
            PràcticaAPI_LISTTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.Pantalla1.route
                    ) {
                        composable(Routes.Pantalla1.route) { LaunchScreen(navigationController) }
                        composable(Routes.Pantalla2.route) { ListScreen(navigationController) }
                        composable(
                            route = "${Routes.Pantalla3.route}/{displayName}",
                            arguments = listOf(navArgument("displayName") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val displayName = backStackEntry.arguments?.getString("displayName") ?: ""
                            DetailScreen(navigationController, displayName)
                        }
                        composable(Routes.Pantalla4.route) { FavoriteScreen(navigationController) }
                    }
                }
            }
        }
    }
}