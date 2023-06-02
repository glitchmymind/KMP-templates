plugins {
    id("android-setup")
    id("multiplatform-compose-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:compose-uikit:theme"))
                implementation(project(":common:compose-uikit:ui-progress"))
                implementation(project(":common:compose-uikit:ui-image"))
            }
        }
    }
}