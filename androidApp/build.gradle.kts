plugins {
    id("com.android.application")
    id("org.jetbrains.compose")
    kotlin("android")
}

android {
    namespace = "com.glitchi.miltiplatformtemplate.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.glitchi.miltiplatformtemplate.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":common:core"))
    implementation(project(":common:dep-compose"))
    implementation(project(":common:dep-common"))

    implementation(project(":common:features:auth:ui")) // change to api

    implementation(Dependencies.Android.Compose.runtime)
    implementation(Dependencies.Android.Compose.ui)
    implementation(Dependencies.Android.Compose.material)
    implementation(Dependencies.Android.Compose.icons)
    implementation(Dependencies.Android.Compose.tooling)
    implementation(Dependencies.Android.Compose.toolingPreview)
    implementation(Dependencies.Android.Compose.foundation)

    implementation(Dependencies.Android.composeActivity)
}