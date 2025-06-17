package com.brunocamposfonseca.livrosfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.brunocamposfonseca.livrosfirebase.ui.theme.LivrosFirebaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LivrosFirebaseTheme {
                NavController()
            }
        }
    }
}