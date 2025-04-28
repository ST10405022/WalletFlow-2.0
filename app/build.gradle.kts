plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp) //applies the KSP plugin
    id("com.google.dagger.hilt.android") version "2.53.1"
}

android {
    namespace = "com.example.prog7313ui"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.prog7313ui"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Tells KSP where to output schema JSONs
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
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
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.hilt.android) // Hilt

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    ksp (libs.hilt.compiler.vlatestversion)

    // ROOM Database dependencies
    implementation(libs.room.runtime)
    // KSP (Kotlin Symbol Processing) needed to generate Room code
    ksp(libs.androidx.room.compiler)
    // Adds Kotlin extensions and support for Coroutines
    implementation(libs.room.ktx)
}