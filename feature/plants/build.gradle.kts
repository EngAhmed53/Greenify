plugins {
    alias(libs.plugins.greenify.android.library.compose)
    alias(libs.plugins.greenify.android.feature)
}

android {
    namespace = "com.example.greenify.feature.plants"

}

dependencies {
    api(libs.glide.compose)
    ksp(libs.glide.compose)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.androidx.browser)
}