package com.example.prcticaapi_list.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import com.example.prcticaapi_list.R
import com.example.prcticaapi_list.model.Characters
import com.example.prcticaapi_list.viewModel.APIViewModel

@Composable
fun FavoriteScreen(navController: NavController, apiViewModel: APIViewModel) {
    val showLoading by apiViewModel.loading.observeAsState(true)
    val favorits by apiViewModel.favorites.observeAsState(emptyList())
    val searchText by apiViewModel.searchText.observeAsState("")
    val searchStatus by apiViewModel.status.observeAsState(false)
    apiViewModel.getFavorites()

    if (showLoading) {
        CircularProgressIndicator(
            modifier = Modifier.width(50.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    } else {
        val context = LocalContext.current
        val fontFamily = remember {
            FontFamily(
                typeface = ResourcesCompat.getFont(context, R.font.)!!
            )
        }
        Scaffold(
            bottomBar = { BottomBar(navController,) }
        ) { paddingValues ->
            ContingutFavorits(paddingValues, searchStatus, searchText, apiViewModel)
        }
    }
}

@Composable
fun ContingutFavorits(
    paddingValues: PaddingValues,
    searchStatus: Boolean,
    searchText: String,
    favorits: List<Characters>,
    apiViewModel: APIViewModel,
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(paddingValues)
        ) {
            if (searchStatus) {
                SearchBar(apiViewModel, active = true, content = , )
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val filteredCharacters = favorits.filter { valorant ->
                    valorant.displayName.lowercase()
                        .contains(searchText)
                }
            }
        }
    }
}