pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Miltiplatform_template"

include(":androidApp")
include(":desktop")
include(":common:core")
include(":common:dep-ios")
include(":common:dep-compose")
include(":common:dep-common")

include(":common:compose-uikit:theme")
include(":common:compose-uikit:ui-base")
include(":common:compose-uikit:ui-text-fields")
include(":common:compose-uikit:ui-buttons")
include(":common:compose-uikit:ui-progress")
include(":common:compose-uikit:ui-shimmer")
include(":common:compose-uikit:ui-image")

include(":common:navigation-compose")

include(":common:features:auth:api")
include(":common:features:auth:domain")
include(":common:features:auth:presentation")
include(":common:features:auth:ui")
