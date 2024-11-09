plugins {
    alias(libs.plugins.greenify.android.library.compose)
    alias(libs.plugins.greenify.android.feature)
}

android {
    namespace = "com.example.greenify.feature.plants"

}

dependencies {
    implementation(libs.glide.compose)
}