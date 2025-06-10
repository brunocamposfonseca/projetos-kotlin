package com.example.entendendostate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.entendendostate.ui.theme.EntendendoStateTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Criando Variáveis
            var nome by remember { mutableStateOf("") }
            var senha by remember { mutableStateOf("") }
            var login by remember { mutableStateOf("") }


            EntendendoStateTheme {
                Surface (modifier = Modifier.fillMaxSize().padding(10.dp)) {
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(35.dp, 0.dp)
                    ) {
                        Text(
                            text = "Tela de Login",
                            style = TextStyle(
                                fontSize = 40.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                        Spacer(modifier = Modifier.padding(30.dp))
                        Column {
                            OutlinedTextField(
                                value = nome,
                                onValueChange = { novoNome ->
                                    nome = novoNome.trim()
                                },
                                modifier = Modifier.fillMaxWidth(),
                                label = {Text(text = "Nome", fontSize = 16.sp)},
                                singleLine = true,
                                shape = RoundedCornerShape(10.dp),
                                maxLines = 1
                            )
                            Spacer(Modifier.padding(5.dp))
                            OutlinedTextField(
                                value = senha,
                                onValueChange = { novaSenha ->
                                    senha = novaSenha.trim()
                                },
                                modifier = Modifier.fillMaxWidth(),
                                label = {Text(text = "Senha", fontSize = 16.sp)},
                                singleLine = true,
                                shape = RoundedCornerShape(10.dp),
                                maxLines = 1
                            )
                            Spacer(Modifier.padding(15.dp))
                            Button(
                                onClick = { login = nome + "\n" + senha },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp),
                                contentPadding = PaddingValues(18.dp)
                            ) {
                                Text(text = "Login", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        HorizontalDivider(Modifier.padding(0.dp, 20.dp))
                        Text(text = login)
                    }
                }
            }
        }
    }
}