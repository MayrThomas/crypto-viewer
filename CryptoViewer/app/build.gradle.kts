import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    kotlin("plugin.serialization") version "2.1.20"
}

android {
    namespace = "com.mayrthomas.cryptoviewer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mayrthomas.cryptoviewer"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        defaultConfig {
            buildConfigField("String", "API_BASE_URL", "\"https://api.coingecko.com/api/v3/\"")
            buildConfigField("String", "API_KEY", gradleLocalProperties(rootDir, providers).getProperty("api_key"))
            buildConfigField("String", "FAVORITES_PREFS_FILE_NAME", "\"crypt_viewer_favorites\"")
            buildConfigField("String", "USER_PREFS_FILE_NAME", "\"crypt_viewer_user_preferences\"")
        }


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
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //okhttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    //lottie
    implementation(libs.lottie.compose)

    //coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    //compose navigation
    implementation(libs.androidx.navigation.compose)

    //datastore
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.preferences)

    //kotlin serialization
    implementation(libs.kotlinx.serialization.json)

    //chrome custom tab
    implementation(libs.androidx.browser)
}