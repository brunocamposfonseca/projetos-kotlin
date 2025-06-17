package com.example.navband.screens.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navband.R
import com.example.navband.components.DialogNovaPulseira
import com.example.navband.components.DialogNovaPulseiraScan
import com.example.navband.datastore.UserViewModel
import com.example.navband.models.NfcViewModel
import com.example.navband.ui.theme.Blue40
import com.example.navband.ui.theme.cambayFontFamily
import com.example.navband.ui.theme.interFontFamily

@SuppressLint("RememberReturnType", "InvalidColorHexValue")
@Composable
fun HomePage(viewModel: UserViewModel, nfcModel: NfcViewModel)
{
    var bandList = remember { mutableStateMapOf<Int, String>(
        1001 to "Bruno Campos Fonseca",
        1002 to "Samuel Massarana Madalena",
        1003 to "Gustavo Sartorelli de Lima",
        1004 to "Matheus Martin Mota"
    )}

    var dialogNovaPulseiraBasic by remember { mutableStateOf(false) }
    var dialogNovaPulseiraFScreen by remember { mutableStateOf(false) }

    if (dialogNovaPulseiraBasic) {
        DialogNovaPulseira(
            showDialog = { dialogNovaPulseiraBasic = it },
            showDialogFScreen = { dialogNovaPulseiraFScreen = it }
        )
    }

    val context = LocalContext.current

    if (dialogNovaPulseiraFScreen) {
        DialogNovaPulseiraScan(
            showDialog = { dialogNovaPulseiraFScreen = it },
            nfcModel,
            context
        )
    }

    Column (verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(24.dp) ) {
        Text(
            text = "Pulseiras Cadastradas",
            fontFamily = cambayFontFamily,
            fontSize = 26.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
        Card (
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)),
            colors = CardDefaults.cardColors(containerColor = Color(0x0C7A7A7A)),
        ) {
            Column (modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                if(bandList.size > 0){
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier
                    ) {

                        items(bandList.entries.withIndex().toList()) { indexedEntry ->
                            val entry = indexedEntry.value
                            val index = entry.key
                            val value = entry.value

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        modifier = Modifier.weight(1f),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.iconbluetoothband),
                                            contentDescription = "Logo Bluetooth",
                                            modifier = Modifier
                                                .size(35.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                        )

                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = value,
                                                fontFamily = interFontFamily,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Text(
                                                text = "$index",
                                                fontFamily = cambayFontFamily,
                                                fontSize = 12.sp
                                            )
                                        }
                                    }

                                    IconButton(onClick = { bandList.remove(index) }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.linkoff),
                                            contentDescription = "Botão de ícone de desconectar pulseira",
                                            tint = Color.Red
                                        )
                                    }
                                }

                            }
                        }
                    }
                } else {
                    Text(
                        text = "Nenhuma pulseira cadastrada...",
                        color = Color.Gray,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )
                }


                HorizontalDivider(thickness = 2.dp);

                Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Button(
                        onClick = { dialogNovaPulseiraBasic = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Blue40),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Icone de adicionar")
                        Text("Adicionar pulseira", modifier = Modifier.padding(start = 5.dp))
                    }
                }

            }
        }
    }
}

/*
@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun HomePagePreview() {
    HomePage()
}
*/