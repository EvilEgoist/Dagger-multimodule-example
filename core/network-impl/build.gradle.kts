plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
}

android {
    namespace = "ru.multimodule.network_impl"
    compileSdk = SdkVersions.COMPILE_SDK

    defaultConfig {
        minSdk = SdkVersions.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
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

    implementation(project(":core:network-api"))

    implementation (libs.kotlinx.serialization.converter)
    implementation (libs.okhttp.logging.interceptor)
    implementation (libs.kotlinx.serialization)
    implementation (libs.bundles.tests)
    implementation (libs.android.core)
    implementation (libs.appcompat)
    implementation (libs.material)
    implementation (libs.retrofit)
    implementation (libs.dagger)
    kapt(libs.dagger.compiler)
}