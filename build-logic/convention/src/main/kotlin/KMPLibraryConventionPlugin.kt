import com.android.build.gradle.LibraryExtension
import com.tienda3b.app.configureFlavors
import com.tienda3b.app.configureKotlinAndroid
import com.tienda3b.app.configureKotlinMultiplatform
import com.tienda3b.app.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class KMPLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.tienda3b.app.kmp.koin")
                apply("com.tienda3b.app.detekt.plugin")
            }
            configureKotlinMultiplatform()
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 35
                configureFlavors(this)
                resourcePrefix = path
                    .split("""\W""".toRegex())
                    .drop(1).distinct()
                    .joinToString(separator = "_")
                    .lowercase() + "_"
            }
            dependencies {
                add("commonTestImplementation", libs.findLibrary("kotlin.test").get())
                add("commonTestImplementation", libs.findLibrary("kotlinx.coroutines.test").get())
            }
        }
    }
}