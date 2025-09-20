package com.example.usuariosetarefas.models

data class Usuario(
    val id: Int,
    val name: String,
    val username: String,
    val email: String
)

data class Tarefa(
    val id: Int,
    val title: String,
    val completed: Boolean
)
