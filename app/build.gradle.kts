plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp) // applies the KSP plugin
    //alias(libs.plugins.googleServices) //
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

    // Unit tests dependencies
    testImplementation(libs.room.testing)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.junit)
    testImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.room.testing)
    androidTestImplementation(libs.junit)
    // Coroutine test support (runTest, TestScope, etc.)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    // Required for LiveData and Flow testing (InstantTaskExecutorRule)
    testImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.core.testing)

    // ROOM Database dependencies
    implementation(libs.room.runtime)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    // KSP (Kotlin Symbol Processing) needed to generate Room code
    ksp(libs.androidx.room.compiler)
    // Adds Kotlin extensions and support for Coroutines
    implementation(libs.room.ktx)
    testImplementation(kotlin("test"))

}