package com.example.navigation

import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SignupScreen(navController: NavController) {
    var cpf by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 18.dp),
        horizontalAlignment= Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        if (error.isNotBlank()) {
            Card (
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFCCCC), contentColor = Color.Red),
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(1.dp, Color.Red),
                shape = RoundedCornerShape(5.dp)
            ){
                Text(
                    error,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                    textAlign = TextAlign.Center,
                    color = Color.Red, style = TextStyle(
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
        }

        Text("Cadastro", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
        Column(
            horizontalAlignment= Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            OutlinedTextField(value = cpf, onValueChange = { cpf = it}, label = {
                Text("CPF")
            })
            OutlinedTextField(value = nome, onValueChange = { nome = it}, label = {
                Text("Nome")
            })
            OutlinedTextField(value = email, onValueChange = { email = it}, label = {
                Text("Email")
            })
            OutlinedTextField(value = senha, onValueChange = { senha = it}, label = {
                Text("Senha")
            })
        }

        Column(
            horizontalAlignment= Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Button(
                onClick = {if (cpf.isNotBlank() && nome.isNotBlank() && email.isNotBlank() && senha.isNotBlank()) navController.navigate("home/$cpf/$nome/$email/$senha") else error = "Campos inválidos... Insira um valor correto"},
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Text("Cadastrar")
                Icon(Icons.Default.KeyboardArrowRight, modifier = Modifier.size(30.dp), contentDescription = null)
            }
            Text(
                "Já tem login? Fazer Login...",
                Modifier.clickable {
                    navController.navigate("login")
                },
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Italic
                ),
                color = Color.Gray
            )
        }


    }
}