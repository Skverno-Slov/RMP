plugins {
    alias(libs.plugins.android.application)
<<<<<<< HEAD
    alias(libs.plugins.kotlin.android)
=======
>>>>>>> dd3e2d4e8a44c0646e66a34cd1bf6a4c5390f109
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.labwork22"
    compileSdk {
<<<<<<< HEAD
        version = release(34)
=======
        version = release(36)
>>>>>>> dd3e2d4e8a44c0646e66a34cd1bf6a4c5390f109
    }

    defaultConfig {
        applicationId = "com.example.labwork22"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
<<<<<<< HEAD
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    buildToolsVersion = "34.0.0"
=======
    buildFeatures {
        compose = true
    }
>>>>>>> dd3e2d4e8a44c0646e66a34cd1bf6a4c5390f109
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
<<<<<<< HEAD
    implementation(libs.material)
=======
>>>>>>> dd3e2d4e8a44c0646e66a34cd1bf6a4c5390f109
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}