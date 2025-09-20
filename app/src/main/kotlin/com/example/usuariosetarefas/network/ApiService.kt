package com.example.usuariosetarefas.network

import com.example.usuariosetarefas.models.Usuario
import com.example.usuariosetarefas.models.Tarefa
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsuarios(): List<Usuario>

    @GET("users/{id}/todos")
    suspend fun getTarefas(@Path("id") id: Int): List<Tarefa>
}
