package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ui.theme.NavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme {
                Surface {
                    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
                        var navController = rememberNavController()

                        NavHost(navController, "login") {
                            composable("cadastro"){
                                SignupScreen(navController)
                            }
                            composable("home/{cpf}/{nome}/{email}/{senha}"){ valores ->
                                val cpf = valores.arguments?.getString("cpf")
                                val nome = valores.arguments?.getString("nome")
                                val email = valores.arguments?.getString("email")
                                val senha = valores.arguments?.getString("senha")

                                if (nome != null && senha != null) {
                                    HomeScreen(navController, cpf, nome, email, senha)
                                }
                            }

                            composable("login"){
                                LoginScreen(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}