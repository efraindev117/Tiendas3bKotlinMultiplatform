plugins {
    alias(libs.plugins.tienda3b.kmp.library.convention)
    id("kotlinx-serialization")
}

android {
    namespace = "com.tienda3b.app.core.model"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation("androidx.compose.runtime:runtime:1.6.8")

        }
    }
}