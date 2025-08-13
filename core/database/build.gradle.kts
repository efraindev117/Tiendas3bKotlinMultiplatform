plugins {
    alias(libs.plugins.tienda3b.kmp.library.convention)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.androidxRoom)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.tienda3b.app.core.database"
    room {
        schemaDirectory("$projectDir/schemas")
    }
}
kotlin {
    sourceSets.commonMain { kotlin.srcDir("build/generated/ksp/metadata") }
    sourceSets {
        androidMain.dependencies { }
        desktopMain.dependencies { }
        commonMain.dependencies {
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqliteBundled)
            implementation(libs.kotlinx.serialization.json)
            api(projects.core.common)
            api(projects.core.model)
        }
    }
}
dependencies {
    ksp(libs.androidx.room.compiler)
}
