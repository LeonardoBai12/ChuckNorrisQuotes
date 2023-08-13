project.setProperty("mainClassName", "io.lb.chucknorrisquotes.presentation.MainActivityClass")

plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("io.ktor.plugin") version "2.3.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.21"
    id("org.jlleitschuh.gradle.ktlint") version "11.1.0"
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

subprojects {
    apply(from = "${project.rootDir}/ktlint.gradle.kts")
}