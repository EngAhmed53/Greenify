plugins {
    alias(libs.plugins.greenify.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.edumate.greenify.core.data"
}

dependencies {
    implementation(projects.core.network)
    implementation(libs.kotlinx.serialization.json)
}