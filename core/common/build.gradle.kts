plugins {
    alias(libs.plugins.tienda3b.kmp.library.convention)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.tienda3b.app.core.common"
}

kotlin{
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            api(libs.jb.kotlin.stdlib)
        }
        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
        }
        commonTest.dependencies {
            implementation(libs.kotlinx.coroutines.test)
        }
        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}