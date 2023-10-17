import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "org.wolflink.etherion.lib"
version = "1.0.0"
val mainClassPath = "$group.Test"

plugins {
    kotlin("jvm") version "1.9.10"
    application
    `maven-publish`
    id("com.github.johnrengelman.shadow") version ("8.1.1")
}
java {
    withSourcesJar()
}
publishing {
    publications {
        create<MavenPublication>("mavenJava"){
            artifact(tasks.getByName("jar"))
            artifact(tasks.getByName("sourcesJar"))
            artifactId = rootProject.name
            groupId = group.toString()
            version = project.version.toString()
        }
    }
}

application {
    mainClass.set(mainClassPath)
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
//    implementation("org.jfree:jfreechart:1.5.4")
//    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.1")
    implementation("org.icepear.echarts:echarts-java:1.0.7")
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