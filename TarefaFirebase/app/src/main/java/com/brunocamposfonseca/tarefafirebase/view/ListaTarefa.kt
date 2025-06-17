package com.brunocamposfonseca.tarefafirebase.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brunocamposfonseca.tarefafirebase.datasource.DataSource
import com.brunocamposfonseca.tarefafirebase.ui.theme.Purple400
import com.brunocamposfonseca.tarefafirebase.ui.theme.WHITE
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTarefa(navController: NavController){
    val dataSource = DataSource()
    var listaTarefas by remember { mutableStateOf(listOf<Map<String, Any>>()) }
    var mensagem by remember { mutableStateOf("") }

    var scope = rememberCoroutineScope()

    //Carregar lista ao abrir a tela
    LaunchedEffect(Unit) {
        dataSource.listarTarefas(
            onResult = { tarefas -> listaTarefas = tarefas },
            onFailure = { e -> mensagem = "Erro: ${e.message}" }
        )
    }

    var DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    //Menu de navegação
    ModalNavigationDrawer(
        drawerState = DrawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu do App Tarefas", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
            }
        }
    ) {

        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("Minha AppBar") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Purple400,
                        titleContentColor = WHITE
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    DrawerState.apply {
                                        if(isClosed) open() else close()
                                    }
                                }
                            }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Icone do Menu",
                                tint = Color.White,
                                modifier = Modifier.size(30.dp))
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar {
                    Text("Rodapé")
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { navController.navigate("CadastroTarefa") }) {
                    Icon(Icons.Default.Add, contentDescription = "Adicionar")
                }
            }
        ) { innerPadding ->

            Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(listaTarefas) { tarefa ->
                        val t = tarefa["tarefa"] as? String ?: "Sem título"
                        val d = tarefa["descricao"] as? String ?: "Sem descrição"
                        Row(modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)) {
                            Text("📌 $t", fontWeight = FontWeight.Bold)
                            Icon(Icons.Default.Delete,
                                contentDescription = "Icone para apagar uma tarefa",
                                tint = Color.Red,
                                modifier = Modifier.size(24.dp).clickable {
                                    dataSource.deletarTarefa(tarefa["tarefa"].toString())
                                    navController.navigate("ListaTarefa")
                                }
                            )
                        }
                        Row(
                            modifier = Modifier.padding(
                                start = 35.dp,
                                top = 0.dp,
                                end = 10.dp,
                                bottom = 10.dp
                            )
                        ) {
                            Text("$d")
                        }
                        HorizontalDivider()

                    }
                }

                Text(text = mensagem, modifier = Modifier.padding(8.dp))
            }
        }
    }
}