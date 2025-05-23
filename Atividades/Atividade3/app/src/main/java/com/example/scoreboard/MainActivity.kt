package com.example.scoreboard

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scoreboard.ui.theme.ScoreboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScoreboardTheme {
                ViewBoard()
            }
        }
    }
}

@Composable
fun ViewBoard() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
        CardContainer()
    }
}

@Composable
fun CardContainer() {
    var scoreG1 by remember { mutableStateOf(0) }
    var scoreG2 by remember { mutableStateOf(0) }

    Box (modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.padding(20.dp, 80.dp)) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.White
                )
            ) {
                Box() {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column() {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Equipe 1",
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.padding(8.dp))
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = scoreG1.toString(),
                                        style = TextStyle(
                                            fontSize = 40.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            fontStyle = FontStyle.Italic
                                        )
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            BtnAdd(time = 1, score = scoreG1, onScoreChange = { scoreG1 = it })
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(30.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.White
                )
            ) {
                Box() {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column() {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Equipe 2",
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.padding(8.dp))
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = scoreG2.toString(),
                                        style = TextStyle(
                                            fontSize = 40.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            fontStyle = FontStyle.Italic
                                        )
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            BtnAdd(
                                time = 1,
                                score = scoreG2,
                                onScoreChange = { scoreG2 = scoreG2 + 1 })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BtnAdd(time: Int, score: Int, onScoreChange: (Int) -> Unit) {
    Card (
        modifier = Modifier.padding(3.dp).size(100.dp).clickable {
            val newScore = onTap(score)
            onScoreChange(newScore)

                Log.d("Time ${time}", "CreateCircle: ")
            },
        shape = CircleShape
    ) {
        Box (modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Text(
                text = "Add",
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

fun onTap(score: Int): Int {
    return score + 1
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScoreboardTheme {
        ViewBoard()
    }
}