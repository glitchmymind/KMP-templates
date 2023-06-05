plugins {
    id("multiplatform-setup")
    id("android-setup")
    kotlin("plugin.serialization")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                //network
                api(Dependencies.Kotlin.Serialization.serialization)
                api(Dependencies.Kotlin.Coroutines.core)
                api(Dependencies.Ktor.core)
                implementation(Dependencies.Ktor.json)
                implementation(Dependencies.Ktor.serialization)
                implementation(Dependencies.Ktor.negotiation)
                implementation(Dependencies.Ktor.kotlin_json)
                implementation(Dependencies.Ktor.logging)

                //DI
                api(Dependencies.Kodein.core)

                //database
                api(Dependencies.SqlDelight.core)
            }
        }

        androidMain {
            dependencies {
                //network
                implementation(Dependencies.Ktor.android)
                //database
                implementation(Dependencies.SqlDelight.android)
            }
        }

        iosMain {
            dependencies {
                implementation(Dependencies.Ktor.ios)
                implementation(Dependencies.SqlDelight.ios)
            }
        }

        desktopMain {
            dependencies {
                implementation(Dependencies.Ktor.okhttp)
                implementation(Dependencies.SqlDelight.desktop)
            }
        }
    }
}