package com.example.basecomposemvvm.core.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.basecomposemvvm.designsystem.theme.AppIcons
<<<<<<< HEAD
=======
import com.example.basecomposemvvm.designsystem.theme.AppTheme
>>>>>>> 5f9ee58 (update roomdb + viewmodel)
import com.example.basecomposemvvm.feature.budget.BudgetScreen
import com.example.basecomposemvvm.feature.category.CategoryScreen
import com.example.basecomposemvvm.feature.home.HistoryScreen
import com.example.basecomposemvvm.feature.home.HomeScreen
import com.example.basecomposemvvm.feature.report.ReportScreen
import com.example.basecomposemvvm.feature.setting.SettingScreen
<<<<<<< HEAD
import com.example.basecomposemvvm.feature.splash.SplashScreen
=======
// Import màn hình History của bạn (Giả sử bạn đặt tên là TransactionHistoryScreen)
// import com.example.basecomposemvvm.feature.history.TransactionHistoryScreen

object Routes {
    const val HOME = "home"
    const val BUDGET = "budget"
    const val CATEGORY = "category"
    const val REPORT = "report"
    const val SETTING = "setting"
    const val HISTORY = "history" // Thêm route mới cho Lịch sử
}
>>>>>>> 5f9ee58 (update roomdb + viewmodel)

@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavItems = remember {
        listOf(
            BottomNavItem("Home", AppIcons.NavHome, AppDestination.Home.route),
            BottomNavItem("Budget", AppIcons.NavBudget, AppDestination.Budget.route),
            BottomNavItem("Category", AppIcons.NavCategory, AppDestination.Category.route),
            BottomNavItem("Report", AppIcons.NavReport, AppDestination.Report.route),
            BottomNavItem("Setting", AppIcons.NavSettings, AppDestination.Setting.route)
        )
    }

    val showBottomBar = currentDestination?.route in bottomNavItems.map { it.route }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 0.dp
                ) {
                    bottomNavItems.forEach { item ->
                        val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label,
                                    tint = if (selected) MaterialTheme.colorScheme.primary else Color.Gray
                                )
                            },
                            label = {
                                Text(
                                    text = item.label,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = if (selected) FontWeight.Black else FontWeight.Medium,
                                    color = if (selected) MaterialTheme.colorScheme.primary else Color.Gray
                                )
                            },
                            selected = selected,
                            onClick = {
                                if (!selected) {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
<<<<<<< HEAD
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            )
=======
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                            unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurface
>>>>>>> 5f9ee58 (update roomdb + viewmodel)
                        )
                    }
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = AppDestination.Splash.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(AppDestination.Splash) {
                SplashScreen(
                    onNavigateToHome = {
                        navController.navigate(AppDestination.Home.route) {
                            popUpTo(AppDestination.Splash.route) { inclusive = true }
                        }
                    }
                )
            }

<<<<<<< HEAD
            composable(AppDestination.Home) {
                HomeScreen(
                    onNavigateToHistory = { navController.navigate(AppDestination.History.route) },
                    onNavigateToBudget = { navController.navigate(AppDestination.Budget.route) },
                    onNavigateToReport = { navController.navigate(AppDestination.Report.route) },
                    onNavigateToSetting = { navController.navigate(AppDestination.Setting.route) }
                )
            }

            composable(AppDestination.History) {
                HistoryScreen(onBack = { navController.popBackStack() })
            }

            composable(AppDestination.Category) { CategoryScreen() }
            composable(AppDestination.Budget) { BudgetScreen() }
            composable(AppDestination.Report) { ReportScreen() }
            composable(AppDestination.Setting) { SettingScreen() }
        }
=======
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                // Khi bấm See All ở Home, nó sẽ nhảy vào trang HISTORY
                onNavigateToCategory = { navController.navigate(Routes.HISTORY) },
                onNavigateToBudget = { navController.navigate(Routes.BUDGET) },
                onNavigateToReport = { navController.navigate(Routes.REPORT) },
                onNavigateToSetting = { navController.navigate(Routes.SETTING) }
            )
        }
        composable(Routes.BUDGET) { BudgetScreen() }
        composable(Routes.CATEGORY) { CategoryScreen() }
        composable(Routes.REPORT) { ReportScreen() }
        composable(Routes.SETTING) { SettingScreen() }

        composable(Routes.HISTORY) {
            Text("Full Transaction History List Goes Here")
        }
>>>>>>> 5f9ee58 (update roomdb + viewmodel)
    }
}

/**
 * Data class cho các mục của Bottom Navigation
 */
data class BottomNavItem(
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
<<<<<<< HEAD
)

/**
 * Extension function để viết code NavHost ngắn gọn hơn với AppDestination
 */
fun NavGraphBuilder.composable(
    destination: AppDestination,
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) {
    composable(
        route = destination.route,
        arguments = destination.arguments,
        deepLinks = deepLinks,
        content = content,
    )
=======
) {
    Home("Home", AppIcons.NavHome, Routes.HOME),
    Category("Category", AppIcons.NavCategory, Routes.CATEGORY),
    Budget("Budget", AppIcons.NavBudget, Routes.BUDGET),
    Report("Report", AppIcons.NavReport, Routes.REPORT),
    Setting("Setting", AppIcons.NavSettings, Routes.SETTING)
}

@Preview(showBackground = true)
@Composable
fun AppNavigationPreview() {
    AppTheme {
        AppNavigation()
    }
>>>>>>> 5f9ee58 (update roomdb + viewmodel)
}