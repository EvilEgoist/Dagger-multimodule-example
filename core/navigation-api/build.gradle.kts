plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "ru.multimodule.navigation_api"
    compileSdk = SdkVersions.COMPILE_SDK

    defaultConfig {
        minSdk = SdkVersions.MIN_SDK
        targetSdk = SdkVersions.TARGET_SDK

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {

    implementation (libs.bundles.tests)
    implementation (libs.android.core)
    implementation (libs.appcompat)
    implementation (libs.material)

}