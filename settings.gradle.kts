pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven("https://www.jitpack.io")
        maven("https://plugins.gradle.org/m2/")
        maven("https://jogamp.org/deployment/maven")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "Tiendas3b"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":Tiendas3b-android")
include(":Tiendas3b-desktop")
include(":tiendas3b-shared")
include(":core:desingsystem")
include(":core:network")
include(":core:common")
include(":core:database")
include(":core:model")
include(":core:data")
include(":core:domain")
include(":feature:auth")
include(":feature:list-detail")
include(":core:ui")
