package com.example.appleappcomposeupgrade.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appleappcomposeupgrade.ui.screen.DetailScreen
import com.example.appleappcomposeupgrade.ui.screen.HomeScreen
import com.example.appleappcomposeupgrade.ui.viewmodel.MainViewModel
import com.example.appleappcomposeupgrade.ui.viewmodel.MainViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val viewModel: MainViewModel = viewModel(
        factory = MainViewModelFactory("Apple Store Home")
    )

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToDetail = { itemId ->
                    navController.navigate("detail/$itemId")
                }
            )
        }

        composable(
            route = "detail/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: 0

            DetailScreen(
                itemId = itemId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}