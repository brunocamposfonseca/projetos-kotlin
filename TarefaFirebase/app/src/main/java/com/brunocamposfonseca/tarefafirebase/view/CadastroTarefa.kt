package com.brunocamposfonseca.tarefafirebase.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brunocamposfonseca.tarefafirebase.datasource.DataSource
import com.brunocamposfonseca.tarefafirebase.ui.theme.Purple400
import com.brunocamposfonseca.tarefafirebase.ui.theme.WHITE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroTarefa(navController: NavController) {

    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    var message by remember { mutableStateOf("") }

    val dataSource = DataSource()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("listar") }) {
                        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null, tint = WHITE)
                    }
                },
                title = {
                    Text("Cadastrar Tarefas")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple400,
                    titleContentColor = WHITE
                )
            )
        },
        bottomBar = {
            BottomAppBar {
                Text("Rodapé")
            }
        },
        /*
        floatingActionButton = {
            FloatingActionButton(onClick = { /**/ }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        }
        */

    ) { innerPadding ->
        Column (
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = titulo,
                onValueChange = { novoTitulo ->
                    titulo = novoTitulo
                },
                label = {
                    Text("Título da Tarefa")
                }
            )
            OutlinedTextField(
                value = descricao,
                onValueChange = { novaDescricao ->
                    descricao = novaDescricao
                },
                label = {
                    Text("Descrição da Tarefa")
                }
            )

            Spacer(modifier = Modifier.size(20.dp))

            Button(onClick = {
                if (titulo.isNotBlank() && descricao.isNotBlank()) {
                    dataSource.salvarTarefa(
                        titulo,
                        descricao,
                        onFailure = { erro ->
                            message = "❌ Falha ao enviar..."
                        },
                        onSuccess = {
                            message = "✅ Mensagem Cadastrada!"
                            navController.navigate("listar")
                        }
                    )
                }
                titulo = ""
                descricao = ""
            }) {
                Text("Cadastrar tarefa")
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(text = message)

        }
    }
}