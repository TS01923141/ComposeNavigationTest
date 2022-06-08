package com.example.composenavigationtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composenavigationtest.ui.theme.ComposeNavigationTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavScreen()
                }
            }
        }
    }
}

@Composable
fun NavScreen() {
    //建立NavController
    val navController = rememberNavController()
    //建立NavHost，設定開始點為home
    NavHost(navController = navController, startDestination = "home") {
        //建立Graph

        //home screen
        composable(route = "home") {
            HomeScreen(navController = navController)
        }

        //content screen
        composable(
            //帶入參數content
            route = "content/{content}",
            //參數解析
            arguments = listOf(
                navArgument("content") {
                    type = NavType.StringType
                }
            )
        ) {
            ContentScreen(
                navController = navController,
                //套用參數
                it.arguments?.getString("content")
            )
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "home", modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.h3)
        Button(onClick = { navController.navigate("content/${System.currentTimeMillis()}") }) {
            Text(text = "Content Screen")
        }
    }
}

@Composable
fun ContentScreen(navController: NavController, content: String?) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "content\n$content", modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.h3)
    }
}