import org.jetbrains.kotlin.konan.properties.loadProperties
import java.util.Properties

plugins {
    alias(libs.plugins.tienda3b.kmp.library.convention)
    alias(libs.plugins.buildConfig)
    id("kotlinx-serialization")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.tienda3b.app.core.network"
    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
    testOptions {
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.websockets)
            implementation(libs.ktor.client.json)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.ktor.serialization.kotlinx.json)
        }
    }
}

buildConfig {
    val apiKey = loadLocalProperty("constants.properties", "API_KEY")
    buildConfigField("API_KEY", apiKey)

    val catBaseUrl = loadLocalProperty("constants.properties", "CATS_BASE_URL")
    buildConfigField("CATS_BASE_URL", catBaseUrl)
}

fun Project.loadLocalProperty(
    path: String,
    propertyName: String,
): String {
    val localProperties = Properties()
    val localPropertiesFile = project.rootProject.file(path)
    if (localPropertiesFile.exists()) {
        localProperties.load(localPropertiesFile.inputStream())
        return localProperties.getProperty(propertyName)
    } else {
        throw GradleException("can not find property : $propertyName")
    }
}