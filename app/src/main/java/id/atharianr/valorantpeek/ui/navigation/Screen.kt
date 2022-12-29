package id.atharianr.valorantpeek.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object Detail : Screen("home/{agentId}") {
        fun createRoute(agentId: Long) = "home/$agentId"
    }
}