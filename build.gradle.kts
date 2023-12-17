import org.jetbrains.dokka.gradle.DokkaTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath ("org.jetbrains.dokka:android-documentation-plugin:${libs.versions.dokkaVersion.get()}")
        classpath ("com.vanniktech:gradle-maven-publish-plugin:${libs.versions.mavenPublishVersion.get()}")
    }
}

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.spotlessVersion) apply false
    alias(libs.plugins.dokkaVersion) apply false
}
subprojects {
    apply(plugin = "com.diffplug.spotless")
    apply(plugin ="org.jetbrains.dokka")

    // configure all format tasks at once
    tasks.withType<DokkaTask>().configureEach {
        dokkaSourceSets.configureEach {
            outputDirectory.set(rootProject.mkdir("docs/"))
            noAndroidSdkLink.set(false)
        }
//        val dokkaBaseConfiguration = """
//    {
//      "customAssets": ["${file("assets/my-image.png")}"],
//      "customStyleSheets": ["${file("assets/my-styles.css")}"],
//      "footerMessage": "(c) 2023 Hridoy Chandra Das"
//    }
//    """
//        pluginsMapConfiguration.set(
//            mapOf(
//                // fully qualified plugin name to json configuration
//                "org.jetbrains.dokka.base.DokkaBase" to dokkaBaseConfiguration
//            )
//        )
    }
}
true // Needed to make the Suppress annotation work for the plugins block
