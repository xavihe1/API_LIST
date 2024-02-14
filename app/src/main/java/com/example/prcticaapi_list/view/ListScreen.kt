package com.example.prcticaapi_list.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.prcticaapi_list.model.Ability
import com.example.prcticaapi_list.model.Characters
import com.example.prcticaapi_list.model.Data
import com.example.prcticaapi_list.model.Role
import com.example.prcticaapi_list.navigation.Routes
import com.example.prcticaapi_list.viewModel.APIViewModel

@Composable
fun ListScreen(navController: NavController) {
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
    @Composable
    fun MyRecyclerView(myViewModel: APIViewModel) {
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
            Image(painter = painterResource(id = character.displayIcon),
                contentDescription = character.displayName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
            )
            Column {
                Text(
                    text = character.displayName,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Composable
fun BottomBar() {
    BottomNavigation(backgroundColor = Color.Red, contentColor = Color.White) {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text(text = "Home") },
            selected = true,
            onClick = {  }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorite") },
            label = { Text(text = "Favorite") },
            selected = false,
            onClick = {  }
        )
    }
}
