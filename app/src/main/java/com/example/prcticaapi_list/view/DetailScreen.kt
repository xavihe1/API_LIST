package com.example.prcticaapi_list.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.TextUnit
import androidx.navigation.NavController
import com.example.prcticaapi_list.navigation.getCharactersList

@Composable
fun DetailScreen(navController: NavController, displayName: String) {
    val character = getCharactersList().find { it.displayName == displayName }
    if (character != null) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = , contentDescription = "")
            Text(text = "Heading", modifier = Modifier)
            Text(text = "Description")
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
    Image(painter = , contentDescription = "")
    
    Text(text = "HEADING", fontSize = TextUnit.Unspecified, fontStyle = FontStyle.Normal)
    
    Text(text = "")
    }
}
