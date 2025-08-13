plugins {
    alias(libs.plugins.tienda3b.kmp.library.convention)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.tienda3b.app.core.domain"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.model)
            implementation(projects.core.network)
            implementation(projects.core.data)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
        }

        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.koin.android)
        }
        desktopMain.dependencies {
        }
    }
}