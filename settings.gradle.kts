pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) // força usar só aqui
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "UsuariosETarefas"
include(":app")
