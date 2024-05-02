package com.clipie.main.presentation

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.End
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Start
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UploadBottomBar(navController: NavHostController) {
    val tabItems = listOf("Post", "Clip", "Story", "LiveStream")
    val density = LocalDensity.current
    val anchors = with(density) {
        DraggableAnchors {
            Start at -100.dp.toPx()
            Center at 0f
            End at 100.dp.toPx()
        }
    }

    val anchoredDraggableState: AnchoredDraggableState<Float> = AnchoredDraggableState(
        positionalThreshold = { with(density) { 56.dp.toPx() } },
        velocityThreshold = { with(density) { 125.dp.toPx() } },
        initialValue = { 0 },
        anchors = anchors
    )


    Row(
        modifier = Modifier
            .anchoredDraggable(
                state = anchoredDraggableState,
                orientation = Orientation.Horizontal
            )
            .offset(0.dp)
    ) {
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "post")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "post")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "post")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "post")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "post")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "post")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "post")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "post")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "post")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "post")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "post")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "post")
        }

    }


}