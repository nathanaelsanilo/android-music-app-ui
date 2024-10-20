package com.example.musicappui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicappui.Screen
import com.example.musicappui.screensInDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val controller : NavController= rememberNavController()
    val navBackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackEntry?.destination?.route
    val title = remember { mutableStateOf("") } // TODO change screen title

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Home") }, navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "account menu"
                    )
                }
            })
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(screensInDrawer) {
                    itemScreen ->
                        DrawerItem(selected = currentRoute == itemScreen.dRoute, item = itemScreen) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                            if (itemScreen.dRoute == "add-account") {
                                // open add account dialog
                            } else {
                                controller.navigate(itemScreen.dRoute)
                                title.value = itemScreen.dTitle
                            }
                        }
                }
            }
        }
    ) {
        Text(text = "text", modifier = Modifier.padding(it))
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClick: () -> Unit
) {
    val background = if (selected) Color.LightGray else Color.White
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(background)
            .clickable { onDrawerItemClick() }
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            modifier = Modifier.padding(
                end = 8.dp,
                top = 4.dp
            )
        )
        Text(text = item.dTitle, style = MaterialTheme.typography.titleMedium)

    }
}