package me.hib4.wejanganmadyo.presentation.navigator

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import me.hib4.wejanganmadyo.R
import me.hib4.wejanganmadyo.domain.model.Article
import me.hib4.wejanganmadyo.presentation.bookmark.BookmarkScreen
import me.hib4.wejanganmadyo.presentation.bookmark.BookmarkViewModel
import me.hib4.wejanganmadyo.presentation.details.DetailsScreen
import me.hib4.wejanganmadyo.presentation.details.DetailsViewModel
import me.hib4.wejanganmadyo.presentation.home.HomeScreen
import me.hib4.wejanganmadyo.presentation.home.HomeViewModel
import me.hib4.wejanganmadyo.presentation.navgraph.Route
import me.hib4.wejanganmadyo.presentation.navigator.components.BottomNavigationItem
import me.hib4.wejanganmadyo.presentation.navigator.components.WejanganBottomNavigation
import me.hib4.wejanganmadyo.presentation.search.SearchScreen
import me.hib4.wejanganmadyo.presentation.search.SearchViewModel

@Composable
fun WejanganNavigator() {
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value

    val isBottomBarVisible = remember(backStackState) {
        backStackState?.destination?.route != Route.DetailScreen.name
    }

    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    when (backStackState?.destination?.route) {
        Route.HomeScreen.name -> 0
        Route.SearchScreen.name -> 1
        Route.BookmarkScreen.name -> 2
        else -> 0
    }.also { selectedItem = it }

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    Scaffold(
        bottomBar = {
            if (isBottomBarVisible) {
                WejanganBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemSelected = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.name
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.SearchScreen.name
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.BookmarkScreen.name
                            )
                        }
                    }
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        val bottomPadding = paddingValues.calculateBottomPadding()

        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.name,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.name) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.name
                        )
                    },
                    navigateToDetails = {
                        navigateToDetails(
                            navController = navController,
                            article = it
                        )
                    }
                )
            }

            composable(route = Route.DetailScreen.name) {
                val viewModel: DetailsViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let {
                        DetailsScreen(
                            article = it,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() },
                            sideEffect = viewModel.sideEffect
                        )
                    }
            }

            composable(route = Route.SearchScreen.name) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = {
                        navigateToDetails(
                            navController = navController,
                            article = it
                        )
                    }
                )
            }

            composable(route = Route.BookmarkScreen.name) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                BookmarkScreen(
                    state = state,
                    navigateToDetails = {
                        navigateToDetails(
                            navController = navController,
                            article = it
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.name
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(Route.DetailScreen.name)
}