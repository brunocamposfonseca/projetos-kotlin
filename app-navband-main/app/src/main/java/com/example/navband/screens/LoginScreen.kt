package com.example.navband.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navband.datastore.UserViewModel
import com.example.navband.ui.theme.Blue40
import com.example.navband.ui.theme.BlueGrey40
import com.example.navband.ui.theme.cambayFontFamily
import com.example.navband.ui.theme.interFontFamily

@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavController, viewModel: UserViewModel)
{
    Scaffold (
        modifier = Modifier.systemBarsPadding().padding(horizontal = 10.dp, vertical = 0.dp),
        topBar = {
            IconButton(
                onClick = { navController.navigate("auth") },
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Voltar para auth")
            }
        }
    ) { innerPadding ->
        ContentScreenLogin(modifier = Modifier.padding(innerPadding), navController = navController, viewModel)
    }
}

@Composable
fun ContentScreenLogin(modifier: Modifier = Modifier, navController: NavController, viewModel: UserViewModel)
{
    var login by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize().padding(26.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (verticalArrangement = Arrangement.spacedBy(30.dp)) {
            Column (modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Fazer Login",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontFamily = cambayFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = "Faça seu login para continuar!",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontFamily = cambayFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = login,
                    onValueChange = { loginValue ->
                        login = loginValue
                    },
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    placeholder = { Text(text = "Insira o email ou CPF") },
                    label = { Text(text = "Login") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Blue40,
                        unfocusedBorderColor = BlueGrey40,
                        errorBorderColor = Color.Red
                    ),
                )

                Spacer(modifier = Modifier.height(15.dp))

                OutlinedTextField(
                    value = senha,
                    onValueChange = { senhaValue ->
                        senha = senhaValue
                    },
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    placeholder = { Text(text = "Insira sua senha") },
                    label = { Text(text = "Senha") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Blue40,
                        unfocusedBorderColor = BlueGrey40,
                        errorBorderColor = Color.Red
                    ),
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        viewModel.saveToken("token")
                        navController.navigate("home")
                    }, Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF003A91))
                ) {
                    Text(
                        text = "Logar",
                        style = TextStyle(
                            fontFamily = interFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}