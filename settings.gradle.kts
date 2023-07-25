pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Multimodule_test_app"
include(":app")
include (":core:navigation-api")
include(":core:navigation-impl")
include(":core:network-api")
include(":core:network-impl")
