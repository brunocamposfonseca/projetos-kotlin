package com.brunocamposfonseca.livrosfirebase.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.brunocamposfonseca.livrosfirebase.datasource.DataSource
import com.brunocamposfonseca.livrosfirebase.ui.theme.Background
import com.brunocamposfonseca.livrosfirebase.ui.theme.PrimaryBrown
import com.brunocamposfonseca.livrosfirebase.ui.theme.WHITE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Livro(navController: NavController, idLivro: String) {

    val dataSource = DataSource()

    var mensagem by remember { mutableStateOf("") }

    val livro = remember { mutableStateMapOf<String, Any>() }

    LaunchedEffect(Unit) {
        dataSource.pegaLivro(
            uid = idLivro,
            onResult = { livros ->
                Log.d("Respota Busca Livro", livros.toString())
                livro.clear()
                livro.putAll(livros)
           },
            onFailure = { e -> mensagem = "Erro: ${e.message}" }
        )
    }

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
                            navController.navigate("lista")
                        }
                    ) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft, contentDescription = "Icone do Menu",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp))
                    }
                }
            )

        }
    ) { innerPadding ->
        val nomeLivro = livro["nome"].toString()
        val autorLivro = livro["autor"].toString()
        val generoLivro = livro["genero"].toString()
        val sinopseLivro = livro["sinopse"].toString()


        Column(modifier = Modifier.fillMaxSize().padding(innerPadding).background(Background)) {
            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                Text(
                    text = nomeLivro,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Row {
                    Text(text = "Autor: $autorLivro")
                }

                Row {
                    Text(text = "Genero: $generoLivro")
                }

                Row {
                    Text(text = "Sinopse: $sinopseLivro")
                }
            }
        }
    }
}


