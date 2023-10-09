import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "org.wolflink.etherion.lib"
version = "1.0-SNAPSHOT"
val mainClassPath = "$group.Test"

plugins {
    kotlin("jvm") version "1.9.10"
    application
    id("com.github.johnrengelman.shadow") version ("8.1.1")
}

application {
    mainClass.set(mainClassPath)
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.google.code.gson:gson:2.10.1")
}
tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = mainClassPath
    }
}