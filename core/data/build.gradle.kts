plugins {
    alias(libs.plugins.tienda3b.kmp.library.convention)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.tienda3b.app.core.data"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)
            api(projects.core.network)
            implementation(projects.core.database)
            implementation(libs.kotlinx.serialization.json)

            implementation("androidx.paging:paging-common:3.3.6")
        }

        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.koin.android)
            implementation("androidx.paging:paging-runtime:3.3.6")
            implementation("androidx.paging:paging-compose:3.4.0-alpha02")
        }
        desktopMain.dependencies {
            implementation("app.cash.paging:paging-compose-common-jvm:3.3.0-alpha02-0.5.1")
        }
    }
}