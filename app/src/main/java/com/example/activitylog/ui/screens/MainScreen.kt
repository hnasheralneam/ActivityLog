package com.example.activitylog.ui.screens

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { it
        BottomBarNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar( navController: NavHostController){


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var currentDestination = navBackStackEntry?.destination

    NavigationBar{
        screens.forEach {screen->
            AddItem(screen = screen,
                navDestination = currentDestination ,
                navController = navController )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    navDestination: NavDestination?,
    navController: NavHostController
){
    NavigationBarItem(
        icon = {
            Icon(imageVector = screen.icon, contentDescription = " NavBar Icon")
        },
        label = {
            Text(text = screen.title)
        },
        selected = navDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        })
}



var screens = listOf(
    BottomBarScreen.Home,
    BottomBarScreen.Stats,
    BottomBarScreen.History,
    BottomBarScreen.Settings
)

sealed class BottomBarScreen(
    var route: String,
    var icon: ImageVector,
    var title: String
){
    object Home : BottomBarScreen(
        route = "home",
        icon = Icons.Rounded.Home,
        title = "Main"
    )
    object Stats : BottomBarScreen(
        route = "stats",
        icon = Icons.Rounded.BarChart,
        title = "Stats"
    )
    object History : BottomBarScreen(
        route = "history",
        icon = Icons.Rounded.History,
        title = "History"
    )
    object Settings : BottomBarScreen(
        route = "settings",
        icon = Icons.Rounded.Settings,
        title = "Settings"
    )
}

@Composable
fun BottomBarNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Stats.route) {
            StatsScreen()
        }
        composable(route = BottomBarScreen.History.route) {
            HistoryScreen()
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
    }
}
