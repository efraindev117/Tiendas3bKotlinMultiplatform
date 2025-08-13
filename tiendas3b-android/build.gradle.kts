import com.tienda3b.app.AppBuildType
import com.tienda3b.app.dynamicVersion

plugins {
    alias(libs.plugins.tienda3b.android.application.convention)
    alias(libs.plugins.tienda3b.android.application.compose.convention)
    alias(libs.plugins.tienda3b.android.application.flavors.convention)
    id("com.google.devtools.ksp")
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.composeCompiler)
}

val packageNameSpace: String = libs.versions.androidPackageNamespace.get()

android {
    namespace = packageNameSpace
    compileSdk = libs.versions.android.compileSdk.get().toInt()


    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        applicationId = packageNameSpace
        versionName = System.getenv("VERSION") ?: project.dynamicVersion
        versionCode = System.getenv("VERSION_CODE")?.toIntOrNull() ?: 1
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources.excludes.add("jfxmedia.dll")
        resources.excludes.add("**/javafx-media-*.jar")
    }

    buildTypes {
        debug {
            applicationIdSuffix = AppBuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = false
            applicationIdSuffix = AppBuildType.RELEASE.applicationIdSuffix
            isShrinkResources = false
            isDebuggable = false
            isJniDebuggable = false
            //signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        buildFeatures {
            buildConfig = true
        }
        packaging {
            resources {
                excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            }
        }
        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.compose.material3)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.viewmodel)


    implementation(projects.tiendas3bShared)
    implementation(projects.core.domain)
}

dependencyGuard {
    configuration("prodReleaseRuntimeClasspath") {
        modules = true
        tree = true
    }
}