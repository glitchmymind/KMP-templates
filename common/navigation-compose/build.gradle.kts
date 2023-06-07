plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Dependencies.Other.Navigation.compose)
                implementation(Dependencies.Other.Navigation.core)

                implementation(Dependencies.Other.ViewModel.odyssey)

                implementation(project(":common:compose-uikit:theme"))
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