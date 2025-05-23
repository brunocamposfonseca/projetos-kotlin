package com.example.cartaoproduto

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import com.example.cartaoproduto.ui.theme.CartaoProdutoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CartaoProdutoTheme {
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
            Cards("Fone de Ouvido", 69.90)
            Spacer(
                modifier = Modifier.padding(8.dp)
            )
            Cards("iPhone 16", 6599.00)
        }
    }
}

@Composable
fun Cards(name: String, preco: Double) {
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
                    text = "Produto: " + name,
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
                    text = "Preço: R$ " + preco,
                    style = TextStyle(
                        fontStyle = FontStyle.Italic
                    )
                )
                Spacer(
                    modifier = Modifier.padding(10.dp)
                )
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Card(modifier = Modifier
                        .padding(all = 10.dp)
                        .size(100.dp)
                        .clickable {
                            Log.d("Comprou", "${name}")
                        },
                        shape = CircleShape
                    ){
                        Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Text(text = "Comprar")
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CartaProdutoPreview() {
    CartaoProdutoTheme {
        ViewCard()
    }
}