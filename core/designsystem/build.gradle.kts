plugins {
    alias(libs.plugins.greenify.android.library)
    alias(libs.plugins.greenify.android.library.compose)
}

android {
    namespace = "com.example.greenify.core.designsystem"
}

dependencies {
    // Add relevant dependencies here
    implementation(libs.androidx.ui.text.google.fonts)
}