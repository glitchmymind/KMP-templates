plugins {
    id("android-setup")
    id("multiplatform-setup")
    kotlin("native.cocoapods")
}

version = "0.0.1"

kotlin {
    cocoapods {
        summary = "Ios framework"
        homepage = "www.glitchi.com"

        ios.deploymentTarget = "14.0" // 12.0

        framework {
            transitiveExport = false
            isStatic = false // true
            baseName = "CommonSDK"

            export(project(":common:core"))
            export(project(":common:dep-common"))
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:core"))
                implementation(project(":common:dep-common"))
            }
        }

        iosMain {
            dependencies {
                api(project(":common:dep-common"))
            }
        }
    }
}