import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

group = "com.evildayz.code.wipexif"
version = "1.0.0"

repositories {
    mavenCentral()
}

val vJunit = "5.7.2"

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:$vJunit")
}

val appMainClass = "com.evildayz.code.wipexif.TheApp"
val jarName = "wipexif"

application {
    mainClass.set(appMainClass)
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(PASSED, SKIPPED, FAILED)
    }
}

tasks.register("makeJars") {
    dependsOn(":test")
    dependsOn(":makeJarOnly")
}

tasks.register<ShadowJar>("makeJarOnly") {
    dependsOn(":compileJava")
    archiveClassifier.set("fat")
    manifest {
        attributes["Main-Class"] = appMainClass
    }
    mergeServiceFiles()
    mergeServiceFiles()
    archiveBaseName.set(jarName)
    archiveClassifier.set("")
    archiveVersion.set("")
    archiveAppendix.set("")
    from("build/classes/java/main")
}