package com.brunocamposfonseca.livrosfirebase

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brunocamposfonseca.livrosfirebase.views.CadastroLivros
import com.brunocamposfonseca.livrosfirebase.views.ListaLivros
import com.brunocamposfonseca.livrosfirebase.views.Livro

@Composable
fun NavController() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "lista") {
        composable("cadastro") {
            CadastroLivros(navController)
        }

        composable("lista") {
            ListaLivros(navController)
        }

        composable("livro/{idLivro}") { backStackEntry ->
            val livroId = backStackEntry.arguments?.getString("idLivro")
            if (livroId != null) {
                Livro(navController, livroId)
            }
        }
    }
}