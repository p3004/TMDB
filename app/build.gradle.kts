@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.org.tmdb"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.org.tmdb"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val key: String = gradleLocalProperties(rootDir).getProperty("api_key")
        named("release") {
            isMinifyEnabled = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "IMG_BASE_URL", "\"https://image.tmdb.org/t/p/w500\"")
            buildConfigField("String", "API_KEY", key)
        }
        named("debug") {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "IMG_BASE_URL", "\"https://image.tmdb.org/t/p/original\"")
            buildConfigField("String", "API_KEY", key)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    //SystemUIController
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.31.5-beta")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Dagger-hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-compiler:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.navigation:navigation-compose:2.6.0")
    //Mockito
    testImplementation("org.mockito:mockito-core:3.5.10")


    //Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    //Kotlin test dependencies
    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.3")

}

kapt {
    correctErrorTypes = true
}