plugins {
    id("android-setup")
    id("multiplatform-compose-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:compose-uikit:theme"))
                implementation(project(":common:compose-uikit:ui-base"))
//                implementation(project(":common:compose-uikit:ui-shimmer"))
            }
        }
    }
}