package com.example.todolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var login by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(30.dp)
    ) {
        Icon(
            Icons.Sharp.AccountCircle,
            modifier = Modifier.size(120.dp),
            contentDescription = null,
            tint = Color(0xFF6650a4)
        )
        Column(Modifier.padding(top = 40.dp)) {
            OutlinedTextField(
                value = email,
                onValueChange = { newEmail ->
                    email = newEmail.trim()
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Email", fontSize = 16.sp, fontWeight = FontWeight.Medium) },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                maxLines = 1,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color(0xFF000000),
                    focusedLabelColor = Color(0xFF6650a4),
                    focusedBorderColor = Color(0xFF6650a4),
                    unfocusedTextColor = Color(0xFF000000),
                    unfocusedLabelColor = Color(0xFF696969),
                    unfocusedBorderColor = Color(0xFF696969)
                )
            )
            Spacer(Modifier.padding(5.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { newPass ->
                    password = newPass.trim()
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Senha", fontSize = 16.sp, fontWeight = FontWeight.Medium) },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                maxLines = 1,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color(0xFF000000),
                    focusedLabelColor = Color(0xFF6650a4),
                    focusedBorderColor = Color(0xFF6650a4),
                    unfocusedTextColor = Color(0xFF000000),
                    unfocusedLabelColor = Color(0xFF696969),
                    unfocusedBorderColor = Color(0xFF696969)
                )
            )
            Spacer(Modifier.padding(15.dp))
            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(18.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6650a4), contentColor = Color(0xFFFFFFFF))
            ) {
                Text(text = "Login", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Row(modifier = Modifier.padding(top = 14.dp)) {
                Text(
                    "Não tem um login? ",
                    fontSize = 14.sp,
                    color = Color(0xFF666666)
                )
                Text(
                    "Cadastrar",
                    modifier = Modifier.clickable {
                        navController.navigate("signup")
                    },
                    fontSize = 14.sp,
                    color = Color(0xFF666666),
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Medium,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}