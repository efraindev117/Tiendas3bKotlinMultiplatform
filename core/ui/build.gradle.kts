plugins {
    alias(libs.plugins.tienda3b.kmp.library.convention)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "com.tienda3b.core.ui"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.compose.ui.tooling.preview)
        }
        commonMain.dependencies {
            implementation(projects.core.desingsystem)
            implementation(projects.core.model)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(libs.jb.composeNavigation)
            implementation(libs.constraint.layout)
        }
        androidInstrumentedTest.dependencies {
        }
        desktopMain.dependencies {

        }
    }
}
compose.resources {
    publicResClass = true
    generateResClass = always
    packageOfResClass = "com.tienda3b.core.ui.generated.resources"
}
