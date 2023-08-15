plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "ru.multimodule.app"
    compileSdk = SdkVersions.COMPILE_SDK

    defaultConfig {
        applicationId = "ru.multimodule.app"
        minSdk = SdkVersions.MIN_SDK
        targetSdk = SdkVersions.TARGET_SDK
        versionCode = Releases.VERSION_CODE
        versionName = Releases.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = true
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                // List additional ProGuard rules for the given build type here. By default,
                // Android Studio creates and includes an empty rules file for you (located
                // at the root directory of each module).
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {

    implementation(project(":core:navigation-api"))
    implementation(project(":core:navigation-impl"))
    implementation(project(":core:network-api"))
    implementation(project(":core:network-impl"))
    implementation(project(":core:database-api"))
    implementation(project(":core:database-impl"))
    implementation(project(":features:character-list-api"))
    implementation(project(":features:character-list-impl"))
    implementation(project(":features:character-detail-api"))
    implementation(project(":features:character-detail-impl"))
    implementation(project(":core:dagger"))
    implementation(project(":core:utils"))

    implementation (libs.android.core)
    implementation (libs.appcompat)
    implementation (libs.material)
    implementation (libs.bundles.tests)
}