plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yzq.bugsnag_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yzq.bugsnag_app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
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

//    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation("com.google.android.material:material:1.11.0")
//    implementation "com.squareup.okhttp3:okhttp:4.12.0"

    implementation("com.xeonyu:application:1.0.9")
    implementation("com.xeonyu:logger:1.2.1")

//    implementation(project(":bugsnag-android"))
    implementation(project(":bugsnag-android-core"))
    implementation(project(":bugsnag-plugin-android-anr"))
    implementation(project(":bugsnag-plugin-android-ndk"))
}