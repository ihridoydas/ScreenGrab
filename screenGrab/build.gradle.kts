@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.spotlessVersion) apply false
    alias(libs.plugins.dokkaVersion) apply false
    id("com.vanniktech.maven.publish") version "0.25.3"
}

android {
    namespace = "jp.ihridoydas.screengrab"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    spotless {
        kotlin {
            target ("**/screenGrab/*.kt","**/screenGrab/*.gradle.kts")
            targetExclude("$buildDir/**/screenGrab/*.kt")
            targetExclude("bin/**/screenGrab/*.kt")

            // ktlint()
            //prettier() // has its own section below
            licenseHeaderFile (rootProject.file("spotless/copyright.kt"))
        }
    }
    afterEvaluate {

        mavenPublishing {

            coordinates("jp.ihridoydas", "ScreenGrab", "1.0.0-SNAPSHOT")

            pom {
                name.set("screenGrab")
                description.set("Jetpack Compose utility library for converting Composable content into Bitmap image and save the image in local Storage.")
                inceptionYear.set("2023")
                url.set("https://github.com/ihridoydas/ScreenGrab/")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/ihridoydas/ScreenGrab/blob/master/LICENSE")
                        distribution.set("https://github.com/ihridoydas/ScreenGrab/blob/master/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("ihridoydas")
                        name.set("Hridoy Chandra Das")
                        url.set("https://github.com/ihridoydas/")
                    }
                }
                scm {
                    url.set("https://github.com/ihridoydas/ScreenGrab/")
                    connection.set("scm:git:git://github.com/ihridoydas/ScreenGrab.git")
                    developerConnection.set("scm:git:ssh://git@github.com/ihridoydas/ScreenGrab.git")
                }
            }
        }

    }

}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation (libs.androidx.ui.graphics.android)

}