package com.example.navband

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navband.screens.AuthScreen
import com.example.navband.screens.CadastroScreen
import com.example.navband.screens.IndexScreen
import com.example.navband.screens.LoginScreen
import com.example.navband.datastore.UserViewModel
import com.example.navband.models.NfcViewModel
import com.example.navband.screens.NotFound
import com.example.navband.screens.pages.HomePage
import com.example.navband.screens.pages.LocalizarPage
import com.example.navband.screens.pages.PerfilPage

@Composable
fun Navegacao(modifier: Modifier = Modifier, viewModel: UserViewModel, nfcModel: NfcViewModel) {
    val navController = rememberNavController()
    val token by viewModel.token.collectAsState("")
    val startRoute = if (token.isBlank()) "auth" else "home"

    NavHost(navController = navController, startDestination = startRoute) {

        composable("auth") {
            AuthScreen(modifier, navController)
        }

        composable("login") {
            LoginScreen(modifier, navController, viewModel)
        }

        composable("cadastro") {
            CadastroScreen(modifier, navController)
        }

        composable("home") {
            IndexScreen(
                modifier = modifier,
                currentRoute = "home",
                onNavigate = { navController.navigate(it) },
                onBack = { navController.popBackStack() },
                content = {
                    HomePage(viewModel, nfcModel)
                },
                navController = navController,
                viewModel = viewModel
            )
        }

        composable("localizar") {
            IndexScreen(
                modifier = modifier,
                currentRoute = "localizar",
                onNavigate = { navController.navigate(it) },
                onBack = { navController.popBackStack() },
                content = {
                    LocalizarPage()
                },
                viewModel = viewModel,
                navController = navController
            )
        }

        composable("perfil") {
            IndexScreen(
                modifier = modifier,
                currentRoute = "perfil",
                onNavigate = { navController.navigate(it) },
                onBack = { navController.popBackStack() },
                content = {
                    PerfilPage(viewModel)
                },
                viewModel = viewModel,
                navController = navController
            )
        }

        composable("not-found") {
            NotFound(modifier, navController)
        }
    }
}