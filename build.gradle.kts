plugins {
    java
    kotlin("jvm") version "1.6.21"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.2"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.21"
    application
}

group = "gw"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.13.3")
    implementation("org.apache.commons:commons-collections4:4.4")
    testImplementation(kotlin("test"))
}

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}

kotlin {
    sourceSets {
        main {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.2")
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("MainKt")
}

benchmark {
    // Create configurations
    configurations {
        named("main") { // main configuration is created automatically, but you can change its defaults
            warmups = 5 // number of warmup iterations
            iterations = 10 // number of iterations
            iterationTime = 15 // time in seconds per iteration
            outputTimeUnit = "ns"
            mode = "avgt"
            reportFormat = "text"
        }
    }

    // Setup targets
    targets {
        // This one matches compilation base name, e.g. 'jvm', 'jvmTest', etc
        register("main") {
            this as kotlinx.benchmark.gradle.JvmBenchmarkTarget
            jmhVersion = "1.21" // available only for JVM compilations & Java source sets
        }
    }
}
