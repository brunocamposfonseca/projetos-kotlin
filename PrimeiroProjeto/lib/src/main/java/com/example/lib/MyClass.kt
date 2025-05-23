package com.example.lib

fun main() {
    println("Hello World!")

    /* Criando Contas Matemáticas */
    var n1: Float
    var n2: Float
    var op: String

    println("Digite o primeiro número: ")
    n1 = readln().toFloat()
    println("Digite o segundo número: ")
    n2 = readln().toFloat()
    println("Digite o simbolo da operação: ")
    op = readln()

    var soma = soma(n1, n2, op)

    println("O resultado da operação de $n1 $op $n2 = $soma");

    /* Exibindo nome completo */

    val nome = "Bruno"
    val sobrenome = "Campos Fonseca"

    var completo = nomeCompleto(nome, sobrenome)

    println("");
    println(completo);

    /* Verificandp múltiplo */

    println("");
    multiplos();

    /* Contador */

    println("");
    contador();

    fib();
}

fun fib() {
    var a = 0;
    var b = 1;
    var c: Int;
    for (i in 2..10) {
        c = a + b;
        a = b;
        b = c;

        println(b);
    }
}

fun contador() {
    var i = 1;

    while (i < 5){
        print("$i");
        i++;
    }
}

fun multiplos() {
    for (i in 1..100){
        if (i%3 == 0) println("$i é múltiplo de 3");
    }
}

fun soma(a: Float, b: Float, op: String): Float {
    return when(op){
        "+" -> a + b;
        "-" -> a - b;
        "*" -> a * b;
        "/" -> if (b != 0.toFloat()) a / b else 0f;
        else -> {
            0f;
        }
    }
}

fun nomeCompleto(n: String, s: String): String {
    return "$n $s";
}

