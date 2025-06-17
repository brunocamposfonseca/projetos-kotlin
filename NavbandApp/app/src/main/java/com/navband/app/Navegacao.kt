package com.navband.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.navband.app.screens.AuthScreen
import com.navband.app.screens.CadastroScreen
import com.navband.app.screens.IndexScreen
import com.navband.app.screens.LoginScreen
import com.navband.app.datastore.UserViewModel
import com.navband.app.models.NfcViewModel
import com.navband.app.screens.NotFound
import com.navband.app.screens.pages.HomePage
import com.navband.app.screens.pages.LocalizarPage
import com.navband.app.screens.pages.PerfilPage

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