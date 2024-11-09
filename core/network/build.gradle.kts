import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.greenify.android.library)
}

android {
    namespace = "com.edumate.greenify.core.network"
    val token: String = gradleLocalProperties(rootDir, providers).getProperty("TREFLE_TOKEN")

    buildTypes {
        buildTypes.forEach {
            it.buildConfigField("String", "BASE_URL", "\"https://trefle.io/api/v1/\"")
            it.buildConfigField("String", "TREFLE_TOKEN", token)
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.bundles.ktor)
    implementation(projects.core.model)
}