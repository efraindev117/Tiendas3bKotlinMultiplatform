plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.androidxRoom) apply false
    alias(libs.plugins.dependencyGuard) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.kotlin.jvm) apply false
}
object DynamicVersion {
    fun setDynamicVersion(file: File, version: String) {
        val cleanedVersion = version.split('+')[0]
        file.writeText(cleanedVersion)
    }
}
tasks.register("versionFile") {
    val file = File(projectDir, "version.txt")
    DynamicVersion.setDynamicVersion(file, project.version.toString())
}