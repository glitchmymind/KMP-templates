plugins {
    id("multiplatform-setup")
    id("android-setup")
    kotlin("plugin.serialization")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":common:features:auth:api"))
                implementation(project(":common:core"))

                implementation(Dependencies.Kodein.core)
            }
        }
    }
}