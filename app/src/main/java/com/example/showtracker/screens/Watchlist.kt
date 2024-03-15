package com.example.showtracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.showtracker.MainViewModel
import com.example.showtracker.R
import com.example.showtracker.fonts.Typography
import com.example.showtracker.ui.theme.ShowTrackerTheme
import com.example.showtracker.utils.Utils

@Composable
fun Watchlist(viewModel: MainViewModel, controller: NavController) {
    val screenWidth = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp }

    if (viewModel.isInternetAvailable(LocalContext.current)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tv),
                    contentDescription = "TV icon",
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    text = "Watchlist",
                    color = Color.White,
                    fontFamily = Typography.openSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Icon(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = "Filter icon",
                    modifier = Modifier.size(24.dp),
                    tint = colorResource(id = R.color.pink)
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 12.dp))

            ShowList(showList = viewModel.loadWatchlist(), controller = controller)
        }

    } else {
        NoInternetAvailable(screenWidth = screenWidth)
    }
}

@Preview(showBackground = true)
@Composable
fun WatchlistPreview() {
    ShowTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val viewModel: MainViewModel = viewModel()
            viewModel.setMockLoadedTVShows(Utils.mockWatchlistPreview)
            val controller = rememberNavController()

            Watchlist(viewModel, controller)
        }
    }
}