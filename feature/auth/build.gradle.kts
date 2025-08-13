plugins {
    alias(libs.plugins.tienda3b.cmp.feature.convention)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.tienda3b.feature.auth"
}
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.koin.core.viewmodel)
            implementation(projects.core.desingsystem)
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.core.database)
            implementation(projects.core.common)
            implementation(projects.core.data)
            implementation(projects.feature.listDetail)
        }
    }
}
ksp {
    arg("KOIN_USE_COMPOSE_VIEWMODEL", "true")
}
