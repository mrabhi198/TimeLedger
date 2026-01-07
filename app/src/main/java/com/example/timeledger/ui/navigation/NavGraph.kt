package com.example.timeledger.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.timeledger.ui.stats.StatsScreen
import com.example.timeledger.ui.timer.TimerScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph() {

    val navController = rememberNavController()

    val items = listOf(
        BottomNavItem.Timer,
        BottomNavItem.Stats
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(item.icon, contentDescription = item.label)
                        },
                        label = {
                            Text(item.label)
                        }
                    )
                }
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Timer.route,
            modifier = Modifier.padding(paddingValues)
        ) {

            composable(BottomNavItem.Timer.route) {
                TimerScreen()
            }

            composable(BottomNavItem.Stats.route) {
                StatsScreen()
            }
        }
    }
}

/* ---------------- Bottom Nav Items ---------------- */

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    object Timer : BottomNavItem(
        route = "timer",
        label = "Timer",
        icon = Icons.Filled.Timer
    )

    object Stats : BottomNavItem(
        route = "stats",
        label = "Stats",
        icon = Icons.AutoMirrored.Filled.List
    )
}
