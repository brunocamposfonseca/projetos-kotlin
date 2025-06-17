package com.example.navband.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.navband.datastore.UserViewModel
import com.example.navband.ui.theme.Blue40
import com.example.navband.ui.theme.interFontFamily

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
            NavigationBar(containerColor = Color.White) {
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
                            selectedIconColor = Blue40,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            selectedTextColor = Blue40,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
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
