package com.example.prcticaapi_list.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.prcticaapi_list.model.Ability
import com.example.prcticaapi_list.model.Characters
import com.example.prcticaapi_list.model.Data
import com.example.prcticaapi_list.model.Role
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
                    MyRecyclerView(myViewModel)
                }
            }
        }
    }
}

@Composable
fun MyRecyclerView(navController: NavController, myViewModel: APIViewModel) {
    val showLoading: Boolean by myViewModel.loading.observeAsState(true)
    val characters: Data by myViewModel.characters.observeAsState(Data(emptyList(), 0))
    myViewModel.getCharacters()

    if (showLoading) {
        CircularProgressIndicator(
            modifier = Modifier.width(50.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    } else {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            items(getCharactersList()) { characters ->
                CharacterItem(character = characters) {
                    navController.navigate(Routes.Pantalla3.createRoute(characters.displayName))
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItem(character: Characters, onItemSelected: (String) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemSelected(character.displayName) }
    ) {
        Row {
            GlideImage(model = character.displayIcon[0],
                contentDescription = "Character Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = character.displayName,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

fun getCharactersList(): List<Characters> {
    val ability1 = Ability("Throws a flashbang", "Flashbang", "Q")
    val ability2 = Ability("Heals teammates", "Healing Orb", "E")
    val abiity3 = Ability("Summons a smoke screen", "Smoke Screen", "C")

    val role1 = Role("Leads the charge and disrupts enemies", "Initiator")
    val role2 = Role("Supports the team with healing abilities", "Healer")

    return listOf(
        Characters(listOf(), "A duelist with disruptive abilities", "", "Phoenix", role1),
        Characters(listOf(), "A support character with healing and utility", "", "", role2),
        Characters(listOf(), "A versatile character with both offensive and defensive capabilities", "", "", role1),
    )
}