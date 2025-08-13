package com.tienda3b.app

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

/**
 * Get the dynamic version of the project.
 */
val Project.dynamicVersion
    get() = project.version.toString().split('+')[0]

/**
 * Configures the `detekt` plugin with the [configure] lambda.
 */
inline fun Project.detektGradle(crossinline configure: DetektExtension.() -> Unit) =
    extensions.configure<DetektExtension> {
        configure()
    }

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)