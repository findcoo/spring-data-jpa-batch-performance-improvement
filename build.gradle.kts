import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    mavenCentral()
}

plugins {
    val kotlinVersion: String? = "1.3.21"
    val springBootVersion: String? = "2.1.3.RELEASE"

    idea
    `maven-publish`
    jacoco
    kotlin("jvm") version(kotlinVersion)
    kotlin("kapt") version(kotlinVersion)
    kotlin("plugin.jpa") version(kotlinVersion)
    kotlin("plugin.spring") version(kotlinVersion)
    id("org.springframework.boot") version(springBootVersion)
}

apply(plugin = "io.spring.dependency-management")

group = "com.kakaopay.voyager.dataflow.sample"
version =  "1.0.1.RELEASE"

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}


dependencies {
    api("com.fasterxml.jackson.module:jackson-module-kotlin")
    api(kotlin("stdlib"))
    api(kotlin("reflect"))
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    implementation("com.h2database:h2")
    implementation("mysql:mysql-connector-java")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1")

    testImplementation("org.springframework.batch:spring-batch-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.mockito:mockito-core:2.18.0")
}

