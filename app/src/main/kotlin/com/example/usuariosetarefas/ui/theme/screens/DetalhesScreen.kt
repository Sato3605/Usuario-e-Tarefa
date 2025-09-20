package com.example.usuariosetarefas.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.usuariosetarefas.models.Usuario
import com.google.gson.Gson

@Composable
fun DetalhesScreen(navController: NavController, usuarioJson: String) {
    val usuario = Gson().fromJson(usuarioJson, Usuario::class.java)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "ID: ${usuario.id}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Nome: ${usuario.name}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Username: ${usuario.username}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Email: ${usuario.email}", style = MaterialTheme.typography.bodyLarge)

        Text(
            text = "Abrir Tarefas do Usuário",
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable {
                    navController.navigate("tarefas/${usuario.id}/${usuario.username}")
                },
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Voltar",
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable {
                    navController.navigate("principal") {
                        popUpTo("principal") { inclusive = true }
                    }
                },
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
