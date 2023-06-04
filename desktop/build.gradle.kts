plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm {
        withJava()
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:core"))
//                implementation(project(":common:compose-uikit"))
                implementation(project(":common:dep-common"))
                implementation(project(":common:dep-compose"))

                implementation(Dependencies.Other.Navigation.core)
                implementation(Dependencies.Other.Navigation.compose)
            }
        }
        named("jvmMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "Main_desktopKt"

        nativeDistributions {
            targetFormats(
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi
            )

            packageName = "Template-Desktop"
            packageVersion = "1.0.0"

            windows {
                menuGroup = "Template desktop"
                upgradeUuid = "1231231231232123123"
            }
        }
    }
}