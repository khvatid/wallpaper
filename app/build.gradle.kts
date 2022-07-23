
plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.to_panelka.wallpaper"
        minSdk = 28
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-beta01"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {



    //Jetpack Compose
    val composeUiVersion = "1.2.0-beta03"
    implementation ("androidx.compose.ui:ui:$composeUiVersion")
    implementation ("androidx.compose.ui:ui-tooling-preview:$composeUiVersion")
    implementation("androidx.navigation:navigation-compose:2.4.2")
    debugImplementation ("androidx.compose.ui:ui-tooling:$composeUiVersion")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:$composeUiVersion")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:$composeUiVersion")


    //Material You
    val material3Version = "1.0.0-alpha13"
    implementation ("androidx.compose.material3:material3:$material3Version")


    //Coil
    implementation("io.coil-kt:coil-compose:2.1.0")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.1.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation ("androidx.activity:activity-compose:1.4.0")


    //Android
    implementation ("androidx.core:core-ktx:1.8.0")
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.24.9-beta")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
    

    //Tests
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")

}
