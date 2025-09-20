package com.example.usuariosetarefas.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.usuariosetarefas.models.Usuario
import com.example.usuariosetarefas.network.RetrofitInstance
import com.google.gson.Gson
import java.net.URLEncoder
import kotlinx.coroutines.launch

@Composable
fun PrincipalScreen(navController: NavController) {
    var usuarios by remember { mutableStateOf(listOf<Usuario>()) }
    var errorMessage by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                usuarios = RetrofitInstance.api.getUsuarios()
            } catch (e: Exception) {
                errorMessage = "Erro ao carregar usuários"
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage)
        } else {
            usuarios.forEach { user ->
                Text(
                    text = user.username,
                    modifier = Modifier
                        .clickable {
                            val encodedJson = URLEncoder.encode(Gson().toJson(user), "UTF-8")
                            navController.navigate("detalhes/$encodedJson")
                        }
                        .padding(vertical = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
