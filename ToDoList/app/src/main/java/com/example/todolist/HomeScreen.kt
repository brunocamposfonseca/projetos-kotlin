package com.example.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.StrokeCap

fun Modifier.bottomBorder(color: Color, strokeWidth: Float) = drawBehind {
    val start = Offset(0f, size.height)
    val end = Offset(size.width, size.height)
    drawLine(
        color = color,
        start = start,
        end = end,
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )
}

@Composable
fun HomeScreen(navController: NavController){
    var todo by remember { mutableStateOf("") }

    Column {
        Row (modifier = Modifier.fillMaxWidth().background(Color(0xFF6650a4)),) {
            Text(
                "Tarefas",
                color = Color(0xFFFFFFFF),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(18.dp)
            )
        }
        Column (modifier = Modifier.padding(20.dp)){
            OutlinedTextField(
                value = todo,
                onValueChange = { newTodo ->
                    todo = newTodo.trim()
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Digite sua tarefa... ", fontSize = 16.sp, fontWeight = FontWeight.Medium) },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                maxLines = 1,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color(0xFF000000),
                    focusedLabelColor = Color(0xFF6650a4),
                    focusedBorderColor = Color(0xFF6650a4),
                    unfocusedTextColor = Color(0xFF000000),
                    unfocusedLabelColor = Color(0xFF696969),
                    unfocusedBorderColor = Color(0xFF696969)
                )
            )

            Row (
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 35.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6650a4),
                        contentColor = Color(0xFFFFFFFF)
                    ),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(
                        "Adicionar Tarefa",
                        fontWeight = FontWeight.Bold,
                    )
                }

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFbf0b0b),
                        contentColor = Color(0xFFFFFFFF)
                    ),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(
                        "Apagar",
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Row (
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.bottomBorder(color = Color(0xFF6650a4), strokeWidth = 6.0F)) {
                    Text(
                        "Tarefas a Fazer",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Box(modifier = Modifier.padding(10.dp)) {
                    Text(
                        "Tarefas Concluidas",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }

            Column (
                modifier = Modifier.fillMaxWidth().padding(top = 18.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row (horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text(
                        "1 - Criar Wireframe de Home",
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF666666)
                    )
                    Row (horizontalArrangement = Arrangement.spacedBy(6.dp)){
                        Icon(
                            Icons.Outlined.Delete,
                            contentDescription = null,
                            tint = Color(0xFFbf0b0b),
                            modifier = Modifier.clickable {  }
                        )

                        Icon(
                            Icons.Outlined.Check,
                            contentDescription = null,
                            tint = Color(0xFF14bf0b),
                            modifier = Modifier.clickable {  }
                        )
                    }

                }
                Row (horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text(
                        "2 - Treinar Go Lang",
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF666666)
                    )
                    Row (horizontalArrangement = Arrangement.spacedBy(6.dp)){
                        Icon(
                            Icons.Outlined.Delete,
                            contentDescription = null,
                            tint = Color(0xFFbf0b0b),
                            modifier = Modifier.clickable {  }
                        )

                        Icon(
                            Icons.Outlined.Check,
                            contentDescription = null,
                            tint = Color(0xFF14bf0b),
                            modifier = Modifier.clickable {  }
                        )
                    }

                }
                Row (horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text(
                        "3 - Estudar Mais um pouco",
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF666666)
                    )
                    Row (horizontalArrangement = Arrangement.spacedBy(6.dp)){
                        Icon(
                            Icons.Outlined.Delete,
                            contentDescription = null,
                            tint = Color(0xFFbf0b0b),
                            modifier = Modifier.clickable {  }
                        )

                        Icon(
                            Icons.Outlined.Check,
                            contentDescription = null,
                            tint = Color(0xFF14bf0b),
                            modifier = Modifier.clickable {  }
                        )
                    }

                }
                Row (horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text(
                        "4 - Enviar protótipo para o cliente",
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF666666)
                    )
                    Row (horizontalArrangement = Arrangement.spacedBy(6.dp)){
                        Icon(
                            Icons.Outlined.Delete,
                            contentDescription = null,
                            tint = Color(0xFFbf0b0b),
                            modifier = Modifier.clickable {  }
                        )

                        Icon(
                            Icons.Outlined.Check,
                            contentDescription = null,
                            tint = Color(0xFF14bf0b),
                            modifier = Modifier.clickable {  }
                        )
                    }

                }
            }
        }
    }
}
