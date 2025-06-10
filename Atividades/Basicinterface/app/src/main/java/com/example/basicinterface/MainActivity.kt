package com.example.basicinterface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basicinterface.ui.theme.BasicInterfaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicInterfaceTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
        Surface (modifier = Modifier.padding(10.dp)) {
            Column() {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(65.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.image),
                        contentDescription = "Imagem Cachorro Sorrindo",
                        modifier = Modifier.size(50.dp).clip(shape = RoundedCornerShape(100.dp))
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                        Text(
                            text = "Passagrana",
                            fontWeight = FontWeight.Bold
                        )
                        Row {
                            Text(
                                text = "Job: ",
                                color = Color.DarkGray,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Text(
                                text = "Nóia",
                                color = Color.Gray,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontStyle = FontStyle.Italic
                                )
                            )
                        }
                    }
                }
                HorizontalDivider(modifier = Modifier.height(10.dp))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.fotopost),
                        contentDescription = "Imagem de uma floresta verde",
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(5.dp)),
                        contentScale = ContentScale.FillWidth
                    )
                }
                HorizontalDivider(modifier = Modifier.height(10.dp))
                //
                    var favorito by remember { mutableStateOf(false) }
                //
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        favorito = !favorito
                    }) {
                        Icon(
                            if(favorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Hearth Favorite Icon",
                            modifier = Modifier.size(20.dp),
                            tint = if (favorito) Color.Red else Color.White
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(text = "Curtir")
                    }
                }
            }
        }

}

@Preview(showBackground = true)
@Composable
fun BasicInterfacePreview() {
    BasicInterfaceTheme {
       MyApp()
    }
}