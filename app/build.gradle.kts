import org.jetbrains.kotlin.storage.CacheResetOnProcessCanceled.enabled

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.ffcs.primeiramarcha"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ffcs.primeiramarcha"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

var retrofitVersion = "2.9.0"
var coroutinesVersion = "1.6.0"
var lifeCycleVersion = "2.2.0"
var recyclerViewVersion = "1.3.2"
var jUnitVersion = "4.13.2"
var loggingInterceptor = "4.10.0"
var koinVersion = "2.1.6"
var constraintLayout = "2.1.4"
var swipeRefreshVersion = "1.2.0-alpha01"

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //ConstraintLayout
    implementation ("androidx.constraintlayout:constraintlayout:$constraintLayout")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$loggingInterceptor")

    //Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    //ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifeCycleVersion")
    //LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifeCycleVersion")
    //RecyclerView
    implementation ("androidx.recyclerview:recyclerview:$recyclerViewVersion")

    //Koin
    implementation ("io.insert-koin:koin-android:$koinVersion")
    implementation ("io.insert-koin:koin-androidx-scope:$koinVersion")
    implementation ("io.insert-koin:koin-androidx-viewmodel:$koinVersion")

    //swipeToRefresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:$swipeRefreshVersion")
}