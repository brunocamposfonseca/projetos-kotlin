package com.navband.app.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.navband.app.ui.theme.cambayFontFamily

@Composable

fun DialogNovaPulseira(showDialog: (Boolean) -> Unit, showDialogFScreen: (Boolean) -> Unit) {
    var textFieldDialog by remember { mutableStateOf("") }
    val buttonDialog = textFieldDialog.length == 4

    AlertDialog(
        onDismissRequest = {
            Log.d("dialog_state", "closed")
            showDialog(false)
        },
        dismissButton = {
            TextButton(
                onClick = {
                    Log.d("dialog_state", "closed")
                    showDialog(false)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Red)
            ) {
                Text("Cancelar")
            }
        },
        confirmButton = {
            TextButton(
                enabled = buttonDialog,
                onClick = {
                    Log.d("dialog_state", "closed")
                    textFieldDialog = ""
                    showDialog(false)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Adicionar")
            }
        },
        title = {Text("Adicionar Pulseira", fontFamily = cambayFontFamily)},
        text = {
            Column (verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Column {
                    OutlinedTextField(
                        value = textFieldDialog,
                        onValueChange = { newCode ->
                            textFieldDialog = newCode
                        },
                        label = { Text("Insira seu TFCode") },
                        singleLine = true,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }

                Row (verticalAlignment = Alignment.CenterVertically) {
                    HorizontalDivider(
                        modifier = Modifier
                            .weight(1f),
                        thickness = 1.dp,
                        color = Color.Gray
                    )

                    Text(
                        text = "ou",
                        modifier = Modifier.padding(horizontal = 8.dp),
                        color = Color.Gray
                    )

                    HorizontalDivider(
                        modifier = Modifier
                            .weight(1f),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                }

                Button(
                    onClick = {
                        showDialogFScreen(true)
                        showDialog(false)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Filled.Search, contentDescription = "Icone de Busca")
                    Text(text = "Escanear pulseira")
                }

            }
        },
        shape = RoundedCornerShape(10.dp)
    )
}