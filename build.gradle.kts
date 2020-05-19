import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.7.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    kotlin("plugin.allopen") version "1.3.61"
    kotlin("plugin.jpa") version "1.3.72"
    kotlin("kapt") version "1.3.61"

    // Apply the application plugin to add support for building a CLI application.
    application
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8


repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()

    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")

    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-config")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")

}

application {
    // Define the main class for the application.
    mainClassName = "com.example.ejem1kotlindocker.Ejem1kotlindockerApplicationKt"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.register("buildDocker"){
    dependsOn("build")
    doLast{
        println("buildDocker!!")
        fileList("./build/libs").forEach { file ->
            file.copyTo(File("./ejem1kotlindocker.jar"))
        }
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

fun fileList(dir: String): List<File> =
        file(dir).listFiles { file: File -> file.isFile }.sorted()


