package com.kodesparkle.sportdbfdj.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.kodesparkle.sportdbfdj.presentation.navigation.ScreenNavHost
import com.kodesparkle.sportdbfdj.ui.theme.SportDBFDJTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportDBFDJTheme {
                SportEuroApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SportEuroApp() {
    val navController = rememberNavController()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ScreenNavHost(
            navController = navController
        )
    }
}



