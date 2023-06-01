plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("kapt")
}

kotlin {
    jvm("desktop")
    android()
    ios()
//    iosArm32()
//    iosArm64()
//    iosX64()
//    iosSimulatorArm64()
    // web() will add in the future

//    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//        kotlinOptions.jvmTarget = "11"
//    }
}
