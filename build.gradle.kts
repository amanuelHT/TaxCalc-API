plugins {
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
}

group = "com.amanuelht"
version = "0.0.1-SNAPSHOT"
description = "Demo project for tax calculation API with DevOps practices"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // ✅ Spring Boot test setup (includes JUnit 5 + Mockito)
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    // ✅ Kotlin + JUnit 5 integration
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // ✅ REQUIRED for Gradle 9.x – provides JUnit Platform launcher
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true // 👈 shows println() output in console
        events("passed", "failed", "skipped") // 👈 logs all test statuses
    }
}
