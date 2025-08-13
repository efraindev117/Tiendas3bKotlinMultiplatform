plugins {
    alias(libs.plugins.tienda3b.kmp.library.convention)
    alias(libs.plugins.tienda3b.cmp.feature.convention)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlin.parcelize)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.material3)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.components.resources)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            api(projects.core.desingsystem)
            api(projects.core.data)
            implementation(projects.core.domain)
            api(projects.core.model)
            api(projects.feature.listDetail)
            api(projects.feature.auth)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(compose.desktop.common)
            implementation(projects.feature.listDetail)
            implementation(projects.core.database)
            implementation(projects.core.domain)
        }
    }
}

android {
    namespace = "cmp.shared"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

compose.resources {
    publicResClass = true
    generateResClass = always
    packageOfResClass = "cmp.shared.generated.resources"
}
