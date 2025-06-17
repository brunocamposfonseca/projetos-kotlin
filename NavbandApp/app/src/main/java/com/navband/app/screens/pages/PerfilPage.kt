package com.navband.app.screens.pages

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.navband.app.datastore.UserViewModel

@Composable
fun PerfilPage(viewModel: UserViewModel)
{
    val token by viewModel.token.collectAsState()

    Text("Perfil")
    Text(text = token)

    Button(onClick = {
        viewModel.clearToken()
    }) {
        Text("Limpar Token")
    }
}