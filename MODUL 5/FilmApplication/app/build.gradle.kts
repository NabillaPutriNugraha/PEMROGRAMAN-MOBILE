import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21" // DISESUAIKAN: Menyelaraskan dengan compiler Kotlin utama kamu
    id("com.google.devtools.ksp") version "2.0.21-1.0.26"
}

android {
    namespace = "com.example.filmapplication"
    compileSdk = 34 // DISESUAIKAN: Turun ke 34 sesuai rekomendasi aman Android Gradle Plugin 8.5.0 kamu

    defaultConfig {
        applicationId = "com.example.filmapplication"
        minSdk = 24
        targetSdk = 34 // DISESUAIKAN: Mengikuti compileSdk yang direkomendasikan AGP 8.5.0
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Membaca local.properties dengan aman
        val properties = Properties()
        val propertiesFile = project.rootProject.file("local.properties")
        if (propertiesFile.exists()) {
            properties.load(propertiesFile.inputStream())
        }
        val apiKey = properties.getProperty("TMDB_API_KEY") ?: ""

        buildConfigField("String", "TMDB_API_KEY", "\"$apiKey\"")
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

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

// Mengunci target JVM Kotlin & KSP ke Java 17 tanpa memicu peringatan deprecated
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

dependencies {
    // --- LIBRARY CORE & UI ---
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1")

    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Navigation & Lifecycle Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.4")

    // Logging Utility
    implementation("com.jakewharton.timber:timber:5.0.1")

    // --- KTOR NETWORKING (DISESUAIKAN: Versi Aman dan Serasi dengan Kotlin 2.0) ---
    val ktorVersion = "2.3.12"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    // --- LOCAL DATABASE (ROOM + KSP) ---
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    "ksp"("androidx.room:room-compiler:$roomVersion")

    // --- IMAGE LOADING (COIL) ---
    implementation("io.coil-kt:coil-compose:2.7.0")

    // --- TESTING & DEBUG ---
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}