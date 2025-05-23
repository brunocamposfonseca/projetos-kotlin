package com.example.lib

var alunos = mutableListOf<String>()
var play = true

fun main() {
    while (play){
        var opcao: Int?

        println("");
        println("\n===== MENU =====");
        println("1 - Cadastrar aluno");
        println("2 - Listar alunos");
        println("3 - Buscar aluno");
        println("4 - Remover aluno");
        println("0 - Sair");
        print("Escolha uma opção: ");
        val input = readlnOrNull();
        opcao = input?.toIntOrNull();
        when(opcao) {
            1 -> adicionaAluno();
            2 -> listaAluno();
            3 -> procuraAluno();
            4 -> deletaAluno();
            0 -> exit();
            else -> {
                println("Opção inválida, escolha novamente...");
            }
        }
    }
}

fun verificarString(text: String): Boolean {
    for (c in text) {
        if (c.isDigit()) {
            return true;
        }
    }
    return false;
}

fun exit() {
    play = false;
}

fun deletaAluno() {
    println("");
    print("Insira o nome do aluno: ");
    var nome = readln();
    if (nome.isBlank() || verificarString(nome)){
        println("Tipo de dado inválido");
    } else {
        alunos.remove(nome);
        println("O aluno $nome foi removido com sucesso!");
    }
}

fun procuraAluno() {
    println("");
    print("Insira o nome ou id do aluno: ");
    var procura = readln();
    if (procura.isBlank() || verificarString(procura)){
        alunos.forEachIndexed { i, a ->
            if(i == procura.toInt()){
                println("O nome do aluno de índice $i: $a");
            }
        }
    } else {
        alunos.forEachIndexed { i, a ->
            if(a == procura){
                println("O índice do aluno $a: $i");
            }
        }
    }
}

fun listaAluno() {
    println("");
    println("Lista de Alunos:");
    alunos.forEachIndexed { index, a ->
        println("$index: $a");
    }
}

fun adicionaAluno() {
    println("");
    print("Insira o nome do aluno: ")
    var nome = readln();
    if(nome.isBlank() or verificarString(nome)){
        print("Nome inválido");
    }else {
        alunos.add(nome);
    }
}