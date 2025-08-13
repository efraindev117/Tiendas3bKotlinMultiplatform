import com.tienda3b.app.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.os.OperatingSystem
import org.gradle.kotlin.dsl.dependencies

class CMPFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.tienda3b.app.kmp.library")
                apply("com.tienda3b.app.kmp.koin")
                apply("com.tienda3b.app.detekt.plugin")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("org.jetbrains.compose")
            }
            val os: OperatingSystem = OperatingSystem.current()
            val arch: String = System.getProperty("os.arch")
            val isAarch64: Boolean = arch.contains("aarch64")

            val platform =
                when {
                    os.isWindows -> "win"
                    os.isMacOsX -> "mac"
                    else -> "linux"
                } + if (isAarch64) "-aarch64" else ""

            dependencies {
                add("commonMainImplementation", libs.findLibrary("constraint-layout").get())
                add("commonMainImplementation", libs.findLibrary("koin.compose").get())
                add("commonMainImplementation", libs.findLibrary("koin.compose.viewmodel").get())

                add("commonMainImplementation", libs.findLibrary("jb.composeRuntime").get())
                add("commonMainImplementation", libs.findLibrary("jb.composeViewmodel").get())
                add("commonMainImplementation", libs.findLibrary("jb.lifecycleViewmodel").get())
                add("commonMainImplementation", libs.findLibrary("jb.lifecycleViewmodelSavedState").get())
                add("commonMainImplementation", libs.findLibrary("jb.savedstate").get())
                add("commonMainImplementation", libs.findLibrary("jb.bundle").get())
                add("commonMainImplementation", libs.findLibrary("jb.composeNavigation").get())
                add("commonMainImplementation", libs.findLibrary("kotlinx.collections.immutable").get())

                add("androidMainImplementation", platform(libs.findLibrary("koin-bom").get()))
                add("androidMainImplementation", libs.findLibrary("koin-android").get())
                add("androidMainImplementation", libs.findLibrary("koin.androidx.compose").get())
                add("androidMainImplementation", libs.findLibrary("koin.android").get())
                add("androidMainImplementation", libs.findLibrary("koin.androidx.navigation").get())
                add("androidMainImplementation", libs.findLibrary("koin.androidx.compose").get())
                add("androidMainImplementation", libs.findLibrary("koin.core.viewmodel").get())
            }
        }
    }
}