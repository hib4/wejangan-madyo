package me.hib4.wejanganmadyo.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import me.hib4.wejanganmadyo.presentation.navigator.WejanganNavigator
import me.hib4.wejanganmadyo.presentation.onboarding.OnBoardingScreen
import me.hib4.wejanganmadyo.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Route.AppStartNavigation.name,
            startDestination = Route.OnBoardingScreen.name
        ) {
            composable(route = Route.OnBoardingScreen.name) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.name,
            startDestination = Route.NewsNavigatorScreen.name
        ) {
            composable(route = Route.NewsNavigatorScreen.name) {
                WejanganNavigator()
            }
        }
    }
}