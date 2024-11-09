plugins {
    alias(libs.plugins.greenify.android.library)
    alias(libs.plugins.greenify.android.library.compose)
}

android {
    namespace = "com.example.greenify.core.ui"
}

dependencies {
    // Add relevant dependencies here
    implementation(projects.core.domain)
}