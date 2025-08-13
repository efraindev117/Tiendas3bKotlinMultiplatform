package com.tienda3b.app

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform() {
    extensions.configure<KotlinMultiplatformExtension> {
        applyDefaultHierarchyTemplate()
        jvm("desktop")
        androidTarget()
        compilerOptions {
            freeCompilerArgs.add("-Xexpect-actual-classes")
        }
    }
}