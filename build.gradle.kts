// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // Plugin Android Application não aplicado no projeto raiz
    id("com.android.application") version "8.3.0" apply false
    // Plugin Kotlin Android não aplicado no projeto raiz
    id("org.jetbrains.kotlin.android") version "1.9.25" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
