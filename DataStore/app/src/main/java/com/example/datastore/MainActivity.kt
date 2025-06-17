package com.example.datastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.datastore.datastore.UserPreference
import com.example.datastore.ui.theme.DataStoreTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataStoreTheme {
                Home()
            }
        }
    }
}

@Composable
fun Home(){
    var email by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var resultEmail by remember { mutableStateOf("") }
    var resultTelefone by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        UserPreference.getEmail(context).collect {
            resultEmail = it
        }
    }

    LaunchedEffect(Unit) {
        UserPreference.getTelefone(context).collect {
            resultTelefone = it
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.fillMaxWidth().padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = { Text(text="Email") }
            )

            OutlinedTextField(
                value = telefone,
                onValueChange = {telefone = it},
                label = { Text(text= "Telefone") }
            )
            Button(onClick = {
                scope.launch{
                    if(email.isNotEmpty()){
                        UserPreference.saveEmail(context = context, email = email)
                    }

                    if(telefone.isNotEmpty()) {
                        UserPreference.saveTelefone(context = context, telefone = telefone)
                    }
                }
            }) {
                Text(text = "Salvar")
            }
            Text(resultEmail)
            Text(resultTelefone)
        }
    }
}