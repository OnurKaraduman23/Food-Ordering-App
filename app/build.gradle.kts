plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.udemybitirme2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.udemybitirme2"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        dataBinding = true
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //viewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.2")
    implementation("androidx.activity:activity-ktx:1.7.2")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    //Retrofit

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.13.2")

//////////////////////////////////
    //circle image view
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    //scalable unit text size
    implementation ("com.intuit.ssp:ssp-android:1.0.6")

    //scalable unit size
    implementation ("com.intuit.sdp:sdp-android:1.0.6")

    //room database
    implementation ("androidx.room:room-runtime:2.6.0")
    kapt ("androidx.room:room-compiler:2.6.0")
    implementation ("androidx.room:room-ktx:2.6.0")
    implementation ("com.makeramen:roundedimageview:2.3.0")

    //Lottie
    implementation ("com.airbnb.android:lottie:3.4.0")

    //easy permission

    implementation ("pub.devrel:easypermissions:3.0.0")

    //coroutines core
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")


    implementation ("com.github.bumptech.glide:glide:4.13.2")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.11.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}