package com.example.usuariosetarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.usuariosetarefas.ui.theme.UsuariosETarefasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsuariosETarefasTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "principal") {

        composable("principal") {
            PrincipalScreen(navController)
        }

        composable(
            route = "detalhes/{usuarioJson}",
            arguments = listOf(
                androidx.navigation.navArgument("usuarioJson") { type = androidx.navigation.NavType.StringType }
            )
        ) { backStackEntry ->
            val encodedJson = backStackEntry.arguments?.getString("usuarioJson") ?: ""
            val usuarioJson = java.net.URLDecoder.decode(encodedJson, "UTF-8")
            DetalhesScreen(navController, usuarioJson)
        }

        composable(
            route = "tarefas/{userId}/{username}",
            arguments = listOf(
                androidx.navigation.navArgument("userId") { type = androidx.navigation.NavType.IntType },
                androidx.navigation.navArgument("username") { type = androidx.navigation.NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            val username = backStackEntry.arguments?.getString("username") ?: ""
            TarefasScreen(userId, username)
        }
    }
}

// ==========================================
// Tela Principal
// ==========================================
@Composable
fun PrincipalScreen(navController: NavController) {
    val usuarioId = 1
    val username = "Rafael"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Bem-vindo à Tela Principal!",
            style = MaterialTheme.typography.headlineMedium
        )

        // Navegação para Detalhes
        val usuarioJson = """{"id":$usuarioId,"nome":"$username"}"""
        Text(
            text = "Ir para Detalhes",
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable {
                    val encodedJson = java.net.URLEncoder.encode(usuarioJson, "UTF-8")
                    navController.navigate("detalhes/$encodedJson")
                },
            style = MaterialTheme.typography.bodyLarge
        )

        // Navegação para Tarefas
        Text(
            text = "Ir para Tarefas",
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable {
                    navController.navigate("tarefas/$usuarioId/$username")
                },
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

// ==========================================
// Tela Detalhes
// ==========================================
@Composable
fun DetalhesScreen(navController: NavController, usuarioJson: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Detalhes do Usuário",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Dados recebidos: $usuarioJson",
            modifier = Modifier.padding(top = 8.dp),
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

// ==========================================
// Tela Tarefas
// ==========================================
@Composable
fun TarefasScreen(userId: Int, username: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Tarefas do Usuário",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "ID: $userId",
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Nome: $username",
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

// ==========================================
// Previews
// ==========================================
@Preview(showBackground = true)
@Composable
fun PrincipalPreview() {
    UsuariosETarefasTheme {
        PrincipalScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun DetalhesPreview() {
    UsuariosETarefasTheme {
        DetalhesScreen(navController = rememberNavController(), usuarioJson = "{\"nome\": \"Rafael\"}")
    }
}

@Preview(showBackground = true)
@Composable
fun TarefasPreview() {
    UsuariosETarefasTheme {
        TarefasScreen(userId = 1, username = "Rafael")
    }
}
