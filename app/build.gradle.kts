plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.hilt)
  id("org.jetbrains.kotlin.plugin.compose") // ← no version here; version is defined in root
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

  buildFeatures { compose = true }

  // For Kotlin 2.0.20 use Compose compiler 1.6.10
  composeOptions { kotlinCompilerExtensionVersion = "1.6.10" }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions { jvmTarget = "17" }

  // Make all Kotlin (incl. kapt) use JDK 17
  kotlin { jvmToolchain(17) }
}

dependencies {
  implementation(platform(libs.compose.bom))
  implementation(libs.compose.ui)
  implementation(libs.compose.ui.tooling)
  implementation(libs.compose.material3)
  implementation(libs.compose.activity)
  implementation(libs.compose.navigation)
  implementation(libs.material)

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
  kapt(libs.room.compiler)

  implementation(libs.datastore.prefs)
  implementation(libs.work.runtime)

  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.2.1")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}

kapt {
  correctErrorTypes = true
}
