// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    dependencies{
        val nav_version = "2.5.0"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.9.20-Beta2-1.0.13" apply false
}