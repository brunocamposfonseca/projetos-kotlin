package com.example.cartaousuario

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cartaousuario.ui.theme.CartaoUsuarioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CartaoUsuarioTheme {
                ViewCard()
            }
        }
    }
}

@Composable
fun ViewCard() {
    Surface (modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.inversePrimary) {
        Column(
            modifier = Modifier.padding(20.dp, 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Cards("Bruno Campos Fonseca", "bruno@email.com", "(00) 00000-0000")
            Spacer(
                modifier = Modifier.padding(8.dp)
            )
            Cards("Brunão do Grauzão", "brunaoattinao@email.com", "(15) 00000-0000")
        }
    }
}

@Composable
fun Cards(name: String, email: String, tel: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        ){
            Column {
                Text(
                    text = name,
                    color = Color(0xFFFFFFFF),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                )
                Spacer(
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = email,
                    style = TextStyle(
                        fontStyle = FontStyle.Italic
                    )
                )
                Spacer(
                    modifier = Modifier.padding(2.dp)
                )
                Text(text = tel)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CartaoUsuarioPreview() {
    CartaoUsuarioTheme {
        ViewCard()
    }
}