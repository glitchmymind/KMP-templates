plugins {
    id("android-setup")
    id("multiplatform-compose-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:core"))
                implementation(project(":common:compose-uikit:theme"))
//                implementation(project(":common:features:auth:domain"))
                implementation(project(":common:dep-common"))

                implementation(Dependencies.Other.Navigation.compose)
                implementation(Dependencies.Other.Navigation.core)
            }
        }

        androidMain {
            dependencies {
                implementation(project(":common:compose-uikit:theme"))
                implementation(Dependencies.Android.composeActivity)
            }
        }
    }
}