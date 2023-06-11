plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:core"))
                implementation(project(":common:features:auth:api"))

                implementation(Dependencies.Other.ViewModel.core)
            }
        }
    }
}