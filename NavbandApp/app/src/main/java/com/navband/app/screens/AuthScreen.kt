package com.navband.app.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.navband.app.R
import com.navband.app.ui.theme.WHITE
import com.navband.app.ui.theme.cambayFontFamily
import com.navband.app.ui.theme.interFontFamily

@Composable
fun AuthScreen(modifier: Modifier = Modifier, navController: NavController)
{
    Column (modifier = Modifier.fillMaxSize().padding(36.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxSize().systemBarsPadding().imePadding()) {
            Column (modifier = Modifier.fillMaxWidth().align(Alignment.Center), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.logomark), contentDescription = null, modifier = Modifier.size(170.dp))
                /*Text(
                    text = "Conecte-se",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontFamily = cambayFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = "O que você gostaria de fazer?",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontFamily = cambayFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                )*/
            }


            Column(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = { navController.navigate("login") }, Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = WHITE)
                ) {
                    Text(
                        text = "Logar",
                        modifier = Modifier.padding(6.dp),
                        style = TextStyle(
                            fontFamily = interFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                OutlinedButton(
                    onClick = { navController.navigate("cadastro") }, Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = "Cadastrar",
                        modifier = Modifier.padding(6.dp),
                        style = TextStyle(
                            fontFamily = interFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = WHITE
                    )
                }
            }
        }
    }
}