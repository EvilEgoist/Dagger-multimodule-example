plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
}

android {
    namespace = "ru.multimodule.character_detail_impl"
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

    implementation(project(":core:dagger"))
    implementation(project(":core:utils"))
    implementation(project(":core:network-api"))
    implementation(project(":core:database-api"))
    implementation(project(":features:character-detail-api"))

    implementation (libs.bundles.tests)
    implementation (libs.android.core)
    implementation (libs.appcompat)
    implementation (libs.material)
    implementation (libs.fragment)
    implementation (libs.dagger)
    kapt(libs.dagger.compiler)
}