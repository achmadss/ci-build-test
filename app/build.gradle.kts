plugins {
    id ("com.android.application")
    id ("com.google.gms.google-services")
    kotlin("android")
    kotlin("kapt")
}

val keyStorePassword = findProperty("KEYSTORE_PASSWORD")

val aliasName = findProperty("ALIAS_NAME")
val aliasPassword = findProperty("ALIAS_PASSWORD")

android {
    namespace = Dependencies.ApplicationID.testLib
    compileSdk = 33

    defaultConfig {
        applicationId = Dependencies.ApplicationID.testLib
        minSdk = 23
        targetSdk = 33
        versionCode = Version.DefaultConfig.Code.version
        versionName = "" +
                "${Version.DefaultConfig.Name.major}." +
                "${Version.DefaultConfig.Name.minor}." +
                Version.DefaultConfig.Name.patch +
                "(${Version.DefaultConfig.Code.version})"
        multiDexEnabled = true

        vectorDrawables {
            useSupportLibrary = true
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        //https://github.com/onmyway133/blog/issues/285
        create("release"){
            keyAlias = "$aliasName"
            keyPassword = "$aliasPassword"
            storeFile = file("keystore.jks")
            storePassword = "$keyStorePassword"
        }
        //https://github.com/onmyway133/blog/issues/285
        create("dev"){
            keyAlias = "$aliasName"
            keyPassword = "$aliasPassword"
            storeFile = file("keystore.jks")
            storePassword = "$keyStorePassword"
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            multiDexKeepProguard = file("multidex-config.txt")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        create("dev") {
            signingConfig = signingConfigs.getByName("dev")
            isMinifyEnabled = false
            multiDexKeepProguard = file("multidex-config.txt")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            isDebuggable = false
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            jniLibs.pickFirsts += "**/*.so"
        }
    }
}

dependencies {

    implementation(Dependencies.AndroidX.Core.coreKtx)
    implementation(Dependencies.AndroidX.Lifecycle.lifecycleRuntimeKtx)
    implementation(Dependencies.AndroidX.Activity.activityCompose)
    implementation(Dependencies.AndroidX.Compose.Ui.ui)
    implementation(Dependencies.AndroidX.Compose.Ui.uiToolingPreview)
    implementation(Dependencies.AndroidX.Compose.Material.material)
    testImplementation(Dependencies.Junit.junit)
    androidTestImplementation(Dependencies.AndroidX.Test.Ext.junit)
    androidTestImplementation(Dependencies.AndroidX.Test.Espresso.core)
    androidTestImplementation(Dependencies.AndroidX.Compose.Ui.uiTestJunit4)
    debugImplementation(Dependencies.AndroidX.Compose.Ui.uiTooling)
    debugImplementation(Dependencies.AndroidX.Compose.Ui.uiTestManifest)

    // cexup ui
    implementation(Dependencies.Com.Cexup.ui)

    // firebase
    with(Dependencies.Com.Google.Firebase) {
        implementation(platform(bom))
        implementation(auth)
//        implementation(fireStore)
//        implementation(storage)
        implementation(messaging)
        implementation(crashlytics)
        implementation(analytics)
    }
}