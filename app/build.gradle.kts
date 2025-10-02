plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.hilt)
  alias(libs.plugins.ksp)
  // Version comes from the root build.gradle.kts
  id("org.jetbrains.kotlin.plugin.compose")
  id("kotlin-kapt")
}

android {
  namespace = "com.yuli.svastha"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.yuli.svastha"
    minSdk = 26
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables { useSupportLibrary = true }
  }

  buildFeatures {
    compose = true
  }

  // Match Compose UI 1.7.x from your BOM
  composeOptions {
    kotlinCompilerExtensionVersion = "1.7.3"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions {
    jvmTarget = "17"
  }

  // Ensure all Kotlin tasks (including kapt) use JDK 17
  kotlin {
    jvmToolchain(17)
  }
}

dependencies {
  // Compose (BOM controls versions of compose artifacts)
  implementation(platform(libs.compose.bom))
  implementation(libs.compose.ui)
  implementation(libs.compose.ui.tooling)
  implementation(libs.compose.material3)
  implementation(libs.compose.activity)
  implementation(libs.compose.navigation)
  implementation(libs.material)

  // Charts
  implementation(libs.vico.core)
  implementation(libs.vico.compose)

  // Kotlinx / Coroutines / Lifecycle
  implementation(libs.coroutines.core)
  implementation(libs.coroutines.android)
  implementation(libs.serialization.json)
  implementation(libs.lifecycle.runtime)
  implementation(libs.lifecycle.viewmodel)

  // Hilt (kapt)
  implementation(libs.hilt.core)
  kapt(libs.hilt.compiler)
  implementation(libs.hilt.navigation.compose)

  // Networking
  implementation(libs.retrofit.core)
  implementation(libs.retrofit.kotlinx)
  implementation(libs.okhttp.core)
  implementation(libs.okhttp.logging)

  // Room (kapt)
  implementation(libs.room.runtime)
  implementation(libs.room.ktx)
  //kapt(libs.room.compiler)
  ksp(libs.room.compiler)

  // DataStore / WorkManager
  implementation(libs.datastore.prefs)
  implementation(libs.work.runtime)

  // Test
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.2.1")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}

kapt {
  correctErrorTypes = true
}