package com.brunocamposfonseca.tarefafirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brunocamposfonseca.tarefafirebase.ui.theme.Purple400
import com.brunocamposfonseca.tarefafirebase.ui.theme.TarefaFirebaseTheme
import com.brunocamposfonseca.tarefafirebase.ui.theme.WHITE
import com.brunocamposfonseca.tarefafirebase.view.CadastroTarefa
import com.brunocamposfonseca.tarefafirebase.view.ListaTarefa

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TarefaFirebaseTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "listar") {
                    composable("cadastro") {
                        CadastroTarefa(navController)
                    }

                    composable("listar") {
                        ListaTarefa(navController)
                    }
                }
            }
        }
    }
}
