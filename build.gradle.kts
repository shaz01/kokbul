/*
 * Copyright (c) 2023 Olcay Aras.
 */

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt.android) apply false
}

buildscript {
    dependencies {
        classpath(libs.hilt.android.gradlePlugin)
    }
}