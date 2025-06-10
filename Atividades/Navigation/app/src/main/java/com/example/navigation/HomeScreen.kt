package com.example.navigation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController, cpf: String?, nome: String?, email: String?, senha: String?) {
    if (!cpf.isNullOrBlank()&& !nome.isNullOrBlank() && !email.isNullOrBlank() && !senha.isNullOrBlank()){
        Log.d("CPF: ", cpf);
        Log.d("Nome: ", nome);
        Log.d("Email: ", email);
        Log.d("Senha: ", senha);
    }
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
        horizontalAlignment= Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {

        Text("Home", fontSize = 30.sp, fontWeight = FontWeight.Bold)

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            if(!cpf.isNullOrBlank()) Text( "CPF do usuário: " + cpf )
            if(!nome.isNullOrBlank()) Text( "Nome do usuário: " + nome )
            if(!email.isNullOrBlank()) Text( "Email do usuário: " + email )
            if(!senha.isNullOrBlank()) Text( "Senha do usuário: " + senha )
        }

        Button(
            onClick = { navController.navigate("login") },
            colors = ButtonDefaults.buttonColors(Color(0xFFFF0000), Color(0xFFFFFFFF)),
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Icon(Icons.Default.Close, modifier = Modifier.size(30.dp), contentDescription = null)
            Text("Log out")
        }
    }
}