package com.example.usuariosetarefas.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.usuariosetarefas.models.Tarefa
import com.example.usuariosetarefas.network.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun TarefasScreen(userId: Int, username: String) {
    var tarefas by remember { mutableStateOf(listOf<Tarefa>()) }
    var errorMessage by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    LaunchedEffect(userId) {
        scope.launch {
            try {
                tarefas = RetrofitInstance.api.getTarefas(userId)
            } catch (e: Exception) {
                errorMessage = "Erro ao carregar tarefas"
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Tarefas de $username", style = MaterialTheme.typography.headlineMedium)

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage)
        } else {
            tarefas.forEach { tarefa ->
                Text(
                    text = "- ${tarefa.title} [${if (tarefa.completed) "Concluída" else "Pendente"}]",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}
