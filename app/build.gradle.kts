plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.zows.hubxrecruitmentcase"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.zows.hubxrecruitmentcase"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"${project.property("BASE_URL")}\"")
        }

        release {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"${project.property("BASE_URL")}\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    /* hilt */
    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)
    /* room */
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
    /*retrofit */
    implementation(libs.bundles.retrofit)
    implementation(libs.okHttp)
    /*navigation*/
    implementation(libs.bundles.navigation)
    /*resizable dimens */
    implementation(libs.bundles.sspSdp)
    implementation(libs.glide)
    implementation(libs.kotlinx.serialization)
}