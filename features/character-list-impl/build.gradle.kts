plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
}

android {
    namespace = "ru.multimodule.character_list_impl"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(project(":core:dagger"))
    implementation(project(":core:utils"))
    implementation(project(":core:network-api"))
    implementation(project(":core:database-api"))
    implementation(project(":features:character-list-api"))

    implementation (libs.bundles.tests)
    implementation (libs.swipe.refresh)
    implementation (libs.android.core)
    implementation (libs.appcompat)
    implementation (libs.material)
    implementation (libs.fragment)
    implementation (libs.dagger)
    implementation(libs.glide)
    kapt(libs.dagger.compiler)
}