package com.example.prcticaapi_list.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
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
fun BottomBar(
    navigationController: NavHostController,
    bottomNavigationItems: List<BottomNavigationScreens>
) {
    BottomNavigation(backgroundColor = Color.Red) {
        val navBackStackEntry by navigationController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(text = item.label) },
                selected = currentRoute == item.route,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Black,
                alwaysShowLabel = false,
                onClick = {
                    if (currentRoute != item.route) {
                        navigationController.navigate(item.route)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySerachBar(myViewModel: APIViewModel) {
    val searchText by myViewModel.searchText.observeAsState("")

    SearchBar(
        query = searchText,
        onQueryChange = { myViewModel.onSearchTextChange(it) },
        onSearch = { myViewModel.onSearchTextChange(it) },
        active = true,
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search") },
        placeholder = { Text(text = "Què estàs buscant?") },
        onActiveChange = {},
        modifier = Modifier
            .fillMaxHeight(0.1f)
            .clip(CircleShape)
    ) {
        //Searchs List
    }
}