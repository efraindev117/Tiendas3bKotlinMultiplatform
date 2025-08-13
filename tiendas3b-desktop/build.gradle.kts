import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
   alias(libs.plugins.kotlinMultiplatform)
   alias(libs.plugins.composeCompiler)
   alias(libs.plugins.composeMultiplatform)
   alias(libs.plugins.kotlin.serialization)
}

kotlin {
   jvm {
      withJava()
   }
   jvmToolchain(17)

   sourceSets {
      jvmMain.dependencies {
         implementation(libs.kotlinx.coroutines.swing)
         implementation(compose.desktop.currentOs)
         implementation(libs.jb.kotlin.stdlib)
         implementation(libs.koin.core)
         implementation(projects.core.data)
         implementation(projects.core.domain)
         implementation(projects.core.desingsystem)
         implementation(projects.tiendas3bShared)
      }
   }
}

val appName: String = libs.versions.desktopPackageName.get()
val packageNameSpace: String = libs.versions.desktopPackageNamespace.get()
val appVersion: String = libs.versions.desktopPackageVersion.get()


compose.desktop {
   application {
      mainClass = "MainKt"
      nativeDistributions {
         targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Exe, TargetFormat.Deb)
         packageName = appName
         packageVersion = appVersion
         description = "Desktop Application"
         copyright = "Tiendas 3B Examen."
         includeAllModules = true

         windows {
            menuGroup = appName
            shortcut = true
            dirChooser = true
            perUserInstall = true
         }
      }
      buildTypes.release.proguard {
         configurationFiles.from(file("compose-desktop.pro"))
         obfuscate.set(true)
         optimize.set(true)
      }
   }
}

tasks.register<Delete>("cleanDataStore") {
   group = "cleanup"
   description = "Eliminar bases de datos."
   delete(
      fileTree(projectDir) {
         include("**/*.pb", "**/*.db")
      }
   )
}
tasks.named("clean") {
   dependsOn("cleanDataStore")
}