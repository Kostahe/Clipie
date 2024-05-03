package com.clipie.main.presentation

import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
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
import kotlin.math.abs
import kotlin.math.roundToInt

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
val tabItems = listOf(UploadNavConstant.UploadPost.route, UploadNavConstant.UploadClip.route, UploadNavConstant.UploadStory.route, UploadNavConstant.Livestream.route)
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
    var currentlyPicked by remember {
        mutableStateOf( UploadNavConstant.UploadPost.route)
    }
    IconButton(onClick = {navController.navigate(currentlyPicked)}) {
        Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
    }
    Picker(
        tabItems,
        onValueChanged = {
            currentlyPicked = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        showAmount = 3
    )

}
@Composable
fun <T>Picker(
    list:List<T>,
    showAmount:Int = 3,
    modifier: Modifier = Modifier,
    style:PickerStyle = PickerStyle(),
    onValueChanged:(T)->Unit
) {

    val listCount by remember {
        mutableIntStateOf(list.size)
    }

    val correctionValue by remember {
        if(list.size%2 == 0){
            mutableIntStateOf(1)
        }else{
            mutableIntStateOf(0)
        }
    }

    var dragStartedX by remember {
        mutableFloatStateOf(0f)
    }

    var currentDragX by remember {
        mutableFloatStateOf(0f)
    }

    var oldX by remember {
        mutableFloatStateOf(0f)
    }

    Canvas(
        modifier = modifier
            .pointerInput(true){
                detectDragGestures(
                    onDragStart = { offset ->
                        dragStartedX = offset.x
                    },
                    onDragEnd = {
                        val spacePerItem = size.width/showAmount
                        val rest = currentDragX % spacePerItem

                        val roundUp = abs(rest/spacePerItem).roundToInt() == 1
                        val newX = if(roundUp){
                            if(rest<0){
                                currentDragX + abs(rest) - spacePerItem
                            }else{
                                currentDragX - rest + spacePerItem
                            }
                        }else{
                            if(rest < 0){
                                currentDragX + abs(rest)
                            }else{
                                currentDragX - rest
                            }
                        }
                        currentDragX = newX.coerceIn(
                            minimumValue = -(listCount/2f)*spacePerItem,
                            maximumValue = (listCount/2f-correctionValue)*spacePerItem
                        )
                        val index = (listCount/2)+(currentDragX/spacePerItem).toInt()
                        onValueChanged(list[index])
                        oldX = currentDragX
                    },
                    onDrag = {change,_ ->
                        val changeX = change.position.x
                        val newX = oldX + (dragStartedX-changeX)
                        val spacePerItem = size.width/showAmount
                        currentDragX = newX.coerceIn(
                            minimumValue = -(listCount/2f)*spacePerItem,
                            maximumValue = (listCount/2f-correctionValue)*spacePerItem
                        )
                        val index = (listCount/2)+(currentDragX/spacePerItem).toInt()
                        onValueChanged(list[index])
                    }
                )
            }
    ){

        val spaceForEachItem = size.width/showAmount
        for(i in 0 until listCount){
            val currentX = i * spaceForEachItem - currentDragX -
                    ((listCount-1+correctionValue - showAmount)/2*spaceForEachItem)



            drawContext.canvas.nativeCanvas.apply {
                val y = style.lineLength + 5.dp.toPx() + style.textSize.toPx()

                drawText(
                    list[i].toString(),
                    currentX,
                    y,
                    Paint().apply {
                        textSize = style.textSize.toPx()
                        textAlign = Paint.Align.CENTER
                        isFakeBoldText = true
                    }
                )
            }
        }
    }

}

data class PickerStyle(
    val lineColor:Color = Color.Yellow,
    val lineLength:Float = 30f,
    val textSize: TextUnit = 26.sp
)