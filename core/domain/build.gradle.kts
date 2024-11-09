plugins {
    alias(libs.plugins.greenify.android.library)
}

android {
    namespace = "com.edumate.greenify.core.domain"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.common)
}