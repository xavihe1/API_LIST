package com.example.prcticaapi_list.view

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.prcticaapi_list.viewModel.APIViewModel

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