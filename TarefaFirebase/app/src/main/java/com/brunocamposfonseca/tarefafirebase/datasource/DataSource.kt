package com.brunocamposfonseca.tarefafirebase.datasource

import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class DataSource {
    private val db = FirebaseFirestore.getInstance()

    fun salvarTarefa(tarefa: String, descricao: String, onSuccess: () -> Unit, onFailure: (Any) -> Unit) {
        val tarefaMap = hashMapOf(
            "tarefa" to tarefa,
            "descricao" to descricao
        )

        //var uuid = UUID.randomUUID()

        db.collection("Tarefas")
            .document(tarefa)
            .set(tarefaMap)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { erro ->
                onFailure(erro)
            }
    }

    fun listarTarefas(onResult: (List<Map<String, Any>>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("Tarefas")
            .get()
            .addOnSuccessListener { result ->
                val lista = result.mapNotNull { it.data }
                onResult(lista)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun deletarTarefa(tarefa: String) {
        db.collection("Tarefas").document(tarefa).delete()
    }
}