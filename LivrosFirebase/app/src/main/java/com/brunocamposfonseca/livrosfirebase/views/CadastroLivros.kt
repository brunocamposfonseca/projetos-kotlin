package com.brunocamposfonseca.livrosfirebase.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.brunocamposfonseca.livrosfirebase.datasource.DataSource
import com.brunocamposfonseca.livrosfirebase.ui.theme.Background
import com.brunocamposfonseca.livrosfirebase.ui.theme.PrimaryBrown
import com.brunocamposfonseca.livrosfirebase.ui.theme.TerciaryBrown
import com.brunocamposfonseca.livrosfirebase.ui.theme.WHITE
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroLivros(navController: NavController) {
    val dataSource = DataSource()

    var nome by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var sinopse by remember { mutableStateOf("") }
    
    
    var message by remember { mutableStateOf("") }

    var scope = rememberCoroutineScope()
    
    var DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = DrawerState,
        drawerContent = {
            ModalDrawerSheet (
                drawerContainerColor = WHITE,
                drawerContentColor = contentColorFor(PrimaryBrown)
            ) {
                Text("Biblioteca Online", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 30.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.List, contentDescription = "Ícone de Lista") },
                        colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = PrimaryBrown),
                        label = { Text(text = "Cadastrar livro") },
                        selected = false,
                        onClick = {
                            navController.navigate("lista")
                        }
                    )
                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Add, contentDescription = "Ícone de Adição") },
                        colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = PrimaryBrown),
                        label = { Text(text = "Adicionar Livro") },
                        selected = true,
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
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {

                    Text(
                        text = "Livros Cadastrados",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBrown
                    )

                    HorizontalDivider(color = TerciaryBrown, thickness = 1.5.dp)

                    Column {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = WHITE),
                            value = nome,
                            onValueChange = { novonome ->
                                nome = novonome
                            },
                            label = {
                                Text("Nome do livro")
                            },
                            maxLines = 1
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = WHITE),
                            value = autor,
                            onValueChange = { novoautor ->
                                autor = novoautor
                            },
                            label = {
                                Text("Autor do livro")
                            },
                            maxLines = 1
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = WHITE),
                            value = genero,
                            onValueChange = { novogenero ->
                                genero = novogenero
                            },
                            label = {
                                Text("Gênero do livro")
                            },
                            maxLines = 1
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = WHITE),
                            value = sinopse,
                            onValueChange = { novasinopse ->
                                sinopse = novasinopse
                            },
                            label = {
                                Text("Sinopse do livro")
                            },
                            maxLines = 8
                        )

                        Spacer(modifier = Modifier.size(20.dp))

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            colors =  ButtonDefaults.buttonColors(containerColor = PrimaryBrown),
                            shape = RoundedCornerShape(10.dp),
                            onClick = {
                                if (nome.isNotBlank() && autor.isNotBlank() && genero.isNotBlank() && sinopse.isNotBlank()) {
                                    dataSource.salvarLivro(
                                        nome,
                                        autor,
                                        genero,
                                        sinopse,
                                        onFailure = { erro ->
                                            message = "❌ Falha ao enviar..."
                                        },
                                        onSuccess = {
                                            message = "✅ Livro Cadastrado!"
                                            navController.navigate("lista")
                                        }
                                    )
                                }
                                nome = ""
                                autor = ""
                                sinopse = ""
                            }
                        ) {
                            Text("Cadastrar livro")
                        }

                        Spacer(modifier = Modifier.size(20.dp))
                        Text(text = message)
                    }
                }
            }
        }
    }
}