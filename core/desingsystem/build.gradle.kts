plugins {
    alias(libs.plugins.tienda3b.kmp.library.convention)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.tienda3b.cmp.feature.convention)
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.tienda3b.app.core.designsystem"
}
kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
        }
        androidInstrumentedTest.dependencies {
            implementation(libs.androidx.compose.ui.test)
        }
        androidUnitTest.dependencies {
            implementation(libs.androidx.compose.ui.test)
        }
        commonMain.dependencies {
            implementation(libs.coil.kt)
            implementation(libs.coil.core)
            implementation(libs.coil.network)
            implementation(libs.coil.kt.compose)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.uiUtil)
            implementation(compose.components.resources)
        }
        desktopMain.dependencies {
        }
    }
}



compose.resources {
    publicResClass = true
    generateResClass = always
    packageOfResClass = "com.promoespacios.sidiapp.core.designsystem.generated.resources"
}