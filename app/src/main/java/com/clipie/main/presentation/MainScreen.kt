package com.clipie.main.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.tv.foundation.PivotOffsets
import androidx.tv.foundation.lazy.list.TvLazyRow
import com.clipie.main.presentation.clips.components.ClipsTopBar
import com.clipie.main.presentation.home.components.HomeScreenTopBar
import com.clipie.main.presentation.search.SearchScreenTopBar
import com.clipie.main.presentation.navigation.MainNavConstant
import com.clipie.main.presentation.navigation.MainNavHost
import com.clipie.main.presentation.profile.ProfileScreenTopBar
import com.clipie.main.presentation.upload.navigation.UploadNavConstant

val listOfItems: List<BottomNavigationItem> = listOf(
    BottomNavigationItem(MainNavConstant.Home.route, Icons.Filled.Home, Icons.Outlined.Home, true),
    BottomNavigationItem(
        MainNavConstant.Search.route,
        Icons.Filled.Search,
        Icons.Outlined.Search,
        false
    ),
    BottomNavigationItem(
        MainNavConstant.Upload.route,
        Icons.Filled.AddBox,
        Icons.Outlined.AddBox,
        false
    ),
    BottomNavigationItem(
        MainNavConstant.Clips.route,
        Icons.Filled.PlayArrow,
        Icons.Outlined.PlayArrow,
        false
    ),
    BottomNavigationItem(
        MainNavConstant.Profile.route,
        Icons.Filled.AccountCircle,
        Icons.Outlined.AccountCircle,
        false
    )
)

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = {
            when (currentRoute) {
                MainNavConstant.Home.route -> {
                    HomeScreenTopBar()
                }

                MainNavConstant.Profile.route -> {
                    ProfileScreenTopBar()
                }

                MainNavConstant.Search.route -> {
                    SearchScreenTopBar()
                }

                MainNavConstant.Clips.route -> {
                    ClipsTopBar()
                }

            }
        }, bottomBar = {
            val routesList = UploadNavConstant.entries.map { uploadNavConstant ->
                uploadNavConstant.route
            }
            if (currentRoute.toString() !in routesList) {
                MainBottomBar(navController = navController)
            } else {
                UploadBottomBar(navController = navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        MainNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

data class TopNavigationItem(
    val title: String?,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean
)

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean
)

@Composable
fun MainBottomBar(navController: NavHostController) {
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar {
        listOfItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.title)
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (index == selectedItemIndex) item.selectedIcon
                            else item.unselectedIcon,
                            contentDescription = null,
                            modifier = Modifier.size(45.dp)
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun UploadBottomBar(navController: NavHostController) {
    TvLazyRow(
        pivotOffsets = PivotOffsets(0.5f, 0.5f),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item(1) {
            Card(modifier = Modifier,
                shape = CardDefaults.shape,
                colors = CardDefaults.cardColors(),
                elevation = CardDefaults.cardElevation(),
                border = null,
                content = { Text(text = "Post") })
        }
        item {
            Card(modifier = Modifier,
                shape = CardDefaults.shape,
                colors = CardDefaults.cardColors(),
                elevation = CardDefaults.cardElevation(),
                border = null,
                content = { Text(text = "Story") })
        }
        item {
            Card(modifier = Modifier,
                shape = CardDefaults.shape,
                colors = CardDefaults.cardColors(),
                elevation = CardDefaults.cardElevation(),
                border = null,
                content = { Text(text = "Clip") })
        }
        item {
            Card(modifier = Modifier,
                shape = CardDefaults.shape,
                colors = CardDefaults.cardColors(),
                elevation = CardDefaults.cardElevation(),
                border = null,
                content = { Text(text = "Live") })
        }
    }
}