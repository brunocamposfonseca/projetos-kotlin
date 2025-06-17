package com.brunocamposfonseca.livrosfirebase.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.brunocamposfonseca.livrosfirebase.datasource.DataSource
import com.brunocamposfonseca.livrosfirebase.ui.theme.Background
import com.brunocamposfonseca.livrosfirebase.ui.theme.PrimaryBrown
import com.brunocamposfonseca.livrosfirebase.ui.theme.SecondaryBrown
import com.brunocamposfonseca.livrosfirebase.ui.theme.TerciaryBrown
import com.brunocamposfonseca.livrosfirebase.ui.theme.WHITE
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaLivros(navController: NavController) {
    val dataSource = DataSource()

    var listaLivros by remember { mutableStateOf(listOf<Map<String, Any>>()) }
    var mensagem by remember { mutableStateOf("") }

    var scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        dataSource.listarLivros(
            onResult = { livros ->
                listaLivros = livros
            },
            onFailure = { e -> mensagem = "Erro: ${e.message}" }
        )
    }

    var DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        modifier = Modifier.background(TerciaryBrown),
        drawerState = DrawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Biblioteca Online", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 30.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.List, contentDescription = "Ícone de Lista") },
                        label = { Text(text = "Listar Livros") },
                        selected = true,
                        onClick = {
                            navController.navigate("lista")
                        }
                    )
                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Add, contentDescription = "Ícone de Adição") },
                        label = { Text(text = "Adicionar Livro") },
                        selected = false,
                        onClick = {
                            navController.navigate("cadastro")
                        }
                    )
                }
            }
        }
    ) {
        Scaffold (
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Lista de Livros",
                            fontSize = 16.sp
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = PrimaryBrown,
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
                            Icon(
                                Icons.Default.Menu, contentDescription = "Icone do Menu",
                                tint = Color.White,
                                modifier = Modifier.size(30.dp))
                        }
                    }
                )

            }
        ) { innerPadding ->
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding).background(Background)) {
                Column(modifier = Modifier.padding(30.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    Text(
                        text = "Livros Cadastrados",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBrown
                    )

                    HorizontalDivider(color = TerciaryBrown, thickness = 1.5.dp)

                    LazyColumn (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        items(listaLivros){ livro ->
                            val uid = livro["id"].toString()
                            val nome = livro["nome"].toString()
                            val autor = livro["autor"].toString()
                            val sinopse = livro["sinopse"].toString()


                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = WHITE,
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate("livro/$uid")
                                    }
                            ) {
                                Column (modifier = Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                    Column (modifier = Modifier.fillMaxWidth()) {
                                        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                                            Column (modifier = Modifier.fillMaxWidth(0.8f), horizontalAlignment = Alignment.Start) {
                                                Text(
                                                    text = nome,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 18.sp,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                                Text(
                                                    text = autor,
                                                    modifier = Modifier.fillMaxWidth(),
                                                    fontStyle = FontStyle.Italic,
                                                    fontSize = 14.sp,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                            }


                                            IconButton(onClick = {
                                                dataSource.deletarLivro(uid)
                                                navController.navigate("lista")
                                            }) {
                                                Icon(Icons.Default.Delete, contentDescription = "Botão de deletar livro", tint = Color.Red)
                                            }
                                        }

                                    }
                                    Text(
                                        text = sinopse,
                                        modifier = Modifier.fillMaxWidth(),
                                        maxLines = 6,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}