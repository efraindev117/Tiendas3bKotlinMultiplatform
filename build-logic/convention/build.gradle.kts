import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.tienda3b.app.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    implementation(libs.truth)
}


tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        // Android Plugins
        register("androidApplicationCompose") {
            id = libs.plugins.tienda3b.android.application.compose.convention.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = libs.plugins.tienda3b.android.application.convention.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidFlavors") {
            id = libs.plugins.tienda3b.android.application.flavors.convention.get().pluginId
            implementationClass = "AndroidApplicationFlavorsConventionPlugin"
        }
        // KMP & CMP Plugins
        register("cmpFeature") {
            id = libs.plugins.tienda3b.cmp.feature.convention.get().pluginId
            implementationClass = "CMPFeatureConventionPlugin"
        }
        register("kmpKoin") {
            id = libs.plugins.tienda3b.kmp.koin.convention.get().pluginId
            implementationClass = "KMPKoinConventionPlugin"
        }
        register("kmpLibrary") {
            id = libs.plugins.tienda3b.kmp.library.convention.get().pluginId
            implementationClass = "KMPLibraryConventionPlugin"
        }
        register("detekt") {
            id = libs.plugins.detekt.convention.get().pluginId
            implementationClass = "DetektConventionPlugin"
            description = "Configures detekt for the project"
        }
    }
}