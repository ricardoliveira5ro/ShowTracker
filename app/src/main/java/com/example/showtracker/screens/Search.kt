package com.example.showtracker.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.showtracker.MainViewModel
import com.example.showtracker.fonts.Typography
import com.example.showtracker.model.DummyShow
import com.example.showtracker.ui.theme.ShowTrackerTheme

@Composable
fun Search(initialSearchInput: String) {
    val viewModel: MainViewModel = viewModel()
    val tvShowState by viewModel.tvShowState

    val allShows = remember { DummyShow.shows }

    val (searchInput, setSearchInput) = remember { mutableStateOf(initialSearchInput) }

    val filteredShows by remember(searchInput) {
        derivedStateOf {
            allShows.filter { it.title.contains(searchInput, ignoreCase = true) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = tvShowState.list.size.toString(),
            color = Color.White,
            fontFamily = Typography.openSans,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(12.dp))

        SearchBar(onSearchInputChanged = setSearchInput, onSearchSubmitted = { }, initialSearchInput)
        Spacer(modifier = Modifier.height(12.dp))

        ShowList(showList = filteredShows)
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    ShowTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Search("")
        }
    }
}