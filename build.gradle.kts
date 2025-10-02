// root build.gradle.kts
plugins {
    // Android Gradle Plugin (keep at or above what your Android Studio suggests)
    id("com.android.application") version "8.7.2" apply false

    // Kotlin plugins (align all at 2.1.0)
    id("org.jetbrains.kotlin.android") version "2.1.0" apply false
    id("org.jetbrains.kotlin.jvm") version "2.1.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0" apply false

    // Hilt
    id("com.google.dagger.hilt.android") version "2.55" apply false

    // KSP (ok to keep even if you’re using kapt today)
    id("com.google.devtools.ksp") version "2.1.0-1.0.28" apply false
}

// Optional but helpful: keep all Kotlin artifacts aligned to 2.1.0 everywhere.
// Comment out if you prefer not to enforce globally.
//subprojects {
//    configurations.configureEach {
//        resolutionStrategy.eachDependency {
//            if (requested.group == "org.jetbrains.kotlin") {
//                useVersion("2.1.0")
//            }
//        }
//    }
//}