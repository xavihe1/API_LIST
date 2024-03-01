package com.example.prcticaapi_list.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.prcticaapi_list.model.Characters
import com.example.prcticaapi_list.navigation.BottomNavigationScreens
import com.example.prcticaapi_list.viewModel.APIViewModel

@Composable
fun ListScreen(navController: NavHostController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
      val bottomNavigationItems = listOf(
          BottomNavigationScreens.Home,
          BottomNavigationScreens.Favorite
      )

      Scaffold(bottomBar = { BottomBar(navigationController = navController, bottomNavigationItems) }) { paddingValues ->
          Box(
              modifier = Modifier
                  .fillMaxSize()
                  .padding(paddingValues)
          ) {

          }
      }
    }
}

@Composable
fun Contingut(
    paddingValues: PaddingValues,
    searchStatus: Boolean,
    searchText: String,
    apiViewModel: APIViewModel,
    characters: List<Characters>,
    navigationController: NavController,
    viewModel: APIViewModel
    ) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(paddingValues)
        ) {
            if (searchStatus) {
                MySerachBar(apiViewModel)
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val filteredCharacters = characters.filter { valorant ->
                    valorant.displayName.contains(searchText, ignoreCase = true)
                }
            }
        }
    }
}