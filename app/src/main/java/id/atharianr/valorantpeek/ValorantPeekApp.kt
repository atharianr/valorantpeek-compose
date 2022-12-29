package id.atharianr.valorantpeek

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.atharianr.valorantpeek.ui.navigation.NavigationItem
import id.atharianr.valorantpeek.ui.navigation.Screen
import id.atharianr.valorantpeek.ui.screen.detail.DetailScreen
import id.atharianr.valorantpeek.ui.screen.favorite.FavoriteScreen
import id.atharianr.valorantpeek.ui.screen.home.HomeScreen
import id.atharianr.valorantpeek.ui.screen.profile.ProfileScreen
import id.atharianr.valorantpeek.ui.theme.Blue172336
import id.atharianr.valorantpeek.ui.theme.RedDC3D4B
import id.atharianr.valorantpeek.ui.theme.ValorantPeekJetpackComposeTheme
import id.atharianr.valorantpeek.utils.Constant.AGENT_ID

@Composable
fun ValorantPeekApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Detail.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier
            .background(Blue172336)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { agentId ->
                        navController.navigate(Screen.Detail.createRoute(agentId))
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = { agentId ->
                        navController.navigate(Screen.Detail.createRoute(agentId))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument(AGENT_ID) { type = NavType.LongType })
            ) {
                val id = it.arguments?.getLong(AGENT_ID) ?: -1L
                DetailScreen(
                    agentId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier,
        elevation = 16.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile,
            ),
        )

        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                        )
                    },
                    selectedContentColor = RedDC3D4B,
                    unselectedContentColor = Color.Gray,
                    label = { Text(text = item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ValorantPeekAppPreview() {
    ValorantPeekJetpackComposeTheme {
        ValorantPeekApp()
    }
}