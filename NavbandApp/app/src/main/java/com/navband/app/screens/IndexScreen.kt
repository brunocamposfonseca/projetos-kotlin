package com.navband.app.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import boxShadow
import com.navband.app.datastore.UserViewModel
import com.navband.app.ui.theme.TextLight
import com.navband.app.ui.theme.interFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndexScreen(
    modifier: Modifier = Modifier,
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    onBack: () -> Unit,
    content: @Composable () -> Unit,
    viewModel: UserViewModel,
    navController: NavHostController
) {
    val navItemList = listOf(
        NavItem("Início", "home", Icons.Default.Home),
        NavItem("Localizar", "localizar", Icons.Default.LocationOn),
        NavItem("Perfil", "perfil", Icons.Default.Person)
    )

    val token by viewModel.token.collectAsState()

    if (token.isBlank() || token.isEmpty()) {
        navController.navigate("login")
    }

    val selectedIndex = navItemList.indexOfFirst { it.route == currentRoute }.coerceAtLeast(0)
    val showBackButton = currentRoute !in navItemList.map { it.route }

    val currentPage = when (currentRoute) {
        "home" -> "Início"
        "localizar" -> "Localizar"
        "perfil" -> "Perfil"
        else -> "NavBand"
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = currentPage,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        if (showBackButton) {
                            IconButton(onClick = onBack) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Voltar"
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.Black
                    )
                )
                HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
            }
        },
        bottomBar = {
            Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 13.dp)) {
                NavigationBar(containerColor = Color.Transparent
                ){
                    Row(modifier = Modifier
                        .padding(8.dp)
                        .boxShadow(
                            color = TextLight,
                            offsetX = 6.dp,
                            offsetY = 6.dp,
                            blurRadius = 16.dp,
                            cornerRadius = 20.dp,
                            fullShadow = true
                        )
                        .background(MaterialTheme.colorScheme.onSurface, RoundedCornerShape(20.dp))
                    ) {
                        Log.d("Cor secundária", MaterialTheme.colorScheme.secondary.toString())
                        navItemList.forEachIndexed { index, navItem ->
                            NavigationBarItem(
                                selected = index == selectedIndex,
                                onClick = {
                                    if (currentRoute != navItem.route) {
                                        onNavigate(navItem.route)
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = navItem.icon,
                                        contentDescription = navItem.label.lowercase(),
                                    )
                                },
                                label = {
                                    Text(
                                        text = navItem.label,
                                        fontFamily = interFontFamily,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                                )
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            content()
        }
    }
}

data class NavItem(
    val label: String,
    val route: String,
    val icon: ImageVector
)
