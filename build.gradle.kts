buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.0") // Specify your Android Gradle Plugin version here
        classpath("com.google.gms:google-services:4.4.0")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
}