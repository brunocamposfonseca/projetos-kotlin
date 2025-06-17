package com.example.navband.components

import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.navband.models.NfcViewModel


@Composable
fun DialogNovaPulseiraScan(showDialog: (Boolean) -> Unit, nfcModel: NfcViewModel, context: Context) {

    val nfcAdapter = NfcAdapter.getDefaultAdapter(context)

    Dialog(
        onDismissRequest = { showDialog(false) },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface (
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(0.dp),
            color = Color.White
        ) {
            Column (
                modifier = Modifier.padding(20.dp)
            ) {
                Row (
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton (
                        onClick = { showDialog(false); nfcModel.stopListening() },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Icone de voltar")
                    }
                }

                Column {
                    if (nfcAdapter == null) {
                        Text("Infelizmente seu celular não tem suporte para NFC")
                    } else {

                        if (!nfcAdapter.isEnabled) {
                            val intent = Intent(Settings.ACTION_NFC_SETTINGS)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            context.startActivity(intent)
                        }

                        nfcModel.startListening()

                        val tagData by nfcModel.tagData.collectAsState()

                        if(tagData != null) {
                            Text("Pulseira detectada: $tagData")
                        } else {
                            Text("Escaneando Pulseira...")
                        }

                    }
                }
            }

        }
    }



}