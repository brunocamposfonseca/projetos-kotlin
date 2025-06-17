package com.example.navband.screens.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navband.services.MapScreen
import com.example.navband.ui.theme.Blue40
import com.example.navband.ui.theme.cambayFontFamily
import com.example.navband.ui.theme.interFontFamily
import org.osmdroid.util.GeoPoint


data class CoordList(
    val nome: String,
    val origem: GeoPoint,
    val destino: GeoPoint
)

data class DropdownItem(
    val tfcode: Int,
    val nome: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalizarPage()
{
    var map_activate by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val bandList = remember {
        mutableStateMapOf(
            1001 to CoordList(
                nome = "Bruno Campos Fonseca",
                origem = GeoPoint(40.712776, -74.005974),
                destino = GeoPoint(40.720000, -74.000000)
            ),
            1002 to CoordList(
                nome = "Samuel Massarana Madalena",
                origem = GeoPoint(-23.550520, -46.633308),
                destino = GeoPoint(-23.545000, -46.620000)
            ),
            1003 to CoordList(
                nome = "Gustavo Sartorelli de Lima",
                origem = GeoPoint(48.856613, 2.352222),
                destino = GeoPoint(48.860000, 2.340000)
            ),
            1004 to CoordList(
                nome = "Matheus Martin Mota",
                origem = GeoPoint(35.689487, 139.691711),
                destino = GeoPoint(35.695000, 139.680000)
            )
        )
    }

    val itemDropDown = bandList.map { (key, value) ->
        DropdownItem(tfcode = key, nome = value.nome)
    }


    var selectedItem by remember { mutableStateOf<DropdownItem?>(null) }

    var currentBand = remember {
        mutableStateOf<CoordList?>(bandList[1001])
    }

    when (map_activate){
        true -> {
            MapScreen(info = currentBand.value, map_state = { map_activate = !map_activate; currentBand.value = null; selectedItem = null })
        }
        false -> {
            Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Column (modifier = Modifier.padding(30.dp), verticalArrangement = Arrangement.spacedBy(30.dp)) {
                    Column () {
                        Text(
                            text = "Selecione a Pulseira",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontFamily = cambayFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 30.sp
                        )
                        Text(
                            text = "Escolha uma pulseira para rastreá-la!",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontFamily = interFontFamily,
                            fontSize = 16.sp
                        )
                    }
                    ExposedDropdownMenuBox (
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedItem?.nome ?: "",
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Selecione uma pulseira") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            itemDropDown.forEachIndexed { index, item ->
                                DropdownMenuItem(
                                    text = {
                                        Row (horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Bottom) {
                                            Text(
                                                text = item.nome,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 18.sp
                                            )
                                            Text(
                                                text = "${item.tfcode}",
                                                fontSize = 14.sp,
                                                fontStyle = FontStyle.Italic
                                            )
                                        }
                                    },
                                    onClick = {
                                        selectedItem = item
                                        currentBand.value = bandList[item.tfcode]!!
                                        expanded = false
                                    },
                                    contentPadding = PaddingValues(16.dp)
                                )
                            }
                        }
                    }

                    Row (modifier = Modifier.fillMaxWidth()) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                map_activate = true
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Blue40)
                        ) {
                            Icon(Icons.Filled.LocationOn, contentDescription = "Icone de Ponto de Localização")
                            Text(
                                text = "Buscar",
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun LocalizarPagePreview() {
    LocalizarPage()
}