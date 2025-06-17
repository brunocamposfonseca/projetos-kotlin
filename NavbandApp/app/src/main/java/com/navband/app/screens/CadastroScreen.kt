package com.navband.app.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
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
import androidx.compose.material3.OutlinedButton
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
import com.navband.app.ui.theme.AlertAccent
import com.navband.app.ui.theme.PrimaryNormal
import com.navband.app.ui.theme.TextMuted
import com.navband.app.ui.theme.cambayFontFamily
import com.navband.app.ui.theme.interFontFamily


@Composable
fun CadastroScreen(modifier: Modifier = Modifier, navController: NavController) {
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
        ContentScreenCadastro(modifier = Modifier.padding(innerPadding), navController = navController)
    }
}

@Composable
fun ContentScreenCadastro(modifier: Modifier = Modifier, navController: NavController) {
    var opcaoTela by remember { mutableStateOf(false) }
    var login by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize().padding(26.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!opcaoTela) {
            LoginVerificacao(
                navController = navController,
                login = login,
                onLoginChange = { login = it },
                opcaoTela = opcaoTela,
                onOpcaoTelaChange = { opcaoTela = it }
            )
        } else {
            CadastrarSenha(
                navController = navController,
                senha = senha,
                onSenhaChange = { senha = it },
                opcaoTela = opcaoTela,
                onOpcaoTelaChange = { opcaoTela = it }
            )
        }
    }
}

@Composable
fun LoginVerificacao(
    navController: NavController,
    login: String,
    onLoginChange: (String) -> Unit,
    opcaoTela: Boolean,
    onOpcaoTelaChange: (Boolean) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(30.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Cadastrar",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontFamily = cambayFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = "Registrar uma senha para um perfil já existente",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontFamily = cambayFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = login,
                onValueChange = onLoginChange,
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                placeholder = { Text(text = "Insira o email ou CPF") },
                label = { Text(text = "Login") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryNormal,
                    unfocusedBorderColor = TextMuted,
                    errorBorderColor = AlertAccent
                ),
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = { onOpcaoTelaChange(true) },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF003A91))
                ) {
                    Text(
                        text = "Verificar",
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

@Composable
fun CadastrarSenha(
    navController: NavController,
    senha: String,
    onSenhaChange: (String) -> Unit,
    opcaoTela: Boolean,
    onOpcaoTelaChange: (Boolean) -> Unit
) {

    var confirmaSenha by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(30.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Registrar",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontFamily = cambayFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = "Cadastre a nova senha para continuar",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontFamily = cambayFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = senha,
                onValueChange = onSenhaChange,
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                placeholder = { Text(text = "Insira sua nova senha") },
                label = { Text(text = "Senha") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryNormal,
                    unfocusedBorderColor = TextMuted,
                    errorBorderColor = AlertAccent
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = confirmaSenha,
                onValueChange = { confirmaSenhaValue ->
                    confirmaSenha = confirmaSenhaValue
                },
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                placeholder = { Text(text = "Confirme a nova senha") },
                label = { Text(text = "Confirmar Senha") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryNormal,
                    unfocusedBorderColor = TextMuted,
                    errorBorderColor = AlertAccent
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = { if(senha == confirmaSenha) navController.navigate("home") },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF003A91))
                ) {
                    Text(
                        text = "Cadastrar",
                        style = TextStyle(
                            fontFamily = interFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                OutlinedButton(
                    onClick = { onOpcaoTelaChange(false) },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.5.dp, Color(0xFF003A91))
                ) {
                    Text(
                        text = "Voltar",
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