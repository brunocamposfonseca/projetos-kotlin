package com.brunocamposfonseca.livrosfirebase.datasource

import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.UUID

class DataSource {
    private val db = FirebaseFirestore.getInstance()

    fun salvarLivro(nome: String, autor: String, genero: String, sinopse: String, onSuccess: () -> Unit, onFailure: (Any) -> Unit) {
        var uuid = UUID.randomUUID().toString()

        val livroMap = hashMapOf(
            "id" to uuid,
            "nome" to nome,
            "autor" to autor,
            "genero" to genero,
            "sinopse" to sinopse
        )


        db.collection("Livros")
            .document(uuid.toString())
            .set(livroMap)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { erro ->
                onFailure(erro)
            }
    }

    fun listarLivros(onResult: (List<Map<String, Any>>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("Livros")
            .get()
            .addOnSuccessListener { result ->
                val lista = result.mapNotNull { it.data }
                onResult(lista)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun pegaLivro(uid: String, onResult: (Map<String, Any>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("Livros")
            .document(uid)
            .get()
            .addOnSuccessListener { result ->
                if (result.exists()) {
                    result.data?.let { onResult(it) }
                }
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun deletarLivro(uid: String) {
        db.collection("Livros").document(uid).delete()
    }
}