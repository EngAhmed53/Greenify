plugins {
    alias(libs.plugins.greenify.android.library)
    alias(libs.plugins.greenify.android.library.compose)
}

android {
    namespace = "com.example.greenify.core.ui"
}

dependencies {
    implementation(libs.androidx.ui.text.google.fonts)
    // Add relevant dependencies here
}