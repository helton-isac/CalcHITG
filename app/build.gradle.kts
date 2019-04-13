plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("io.fabric")
}

android {
    compileSdkVersion(Versions.compileSdk)
    defaultConfig {
        applicationId = "br.com.hitg.calculator"

        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)

        versionCode = 16
        versionName = "3.6"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file(System.getenv("KEYSTORE") ?: "KeyStore.jks")
            storePassword = System.getenv("KEYSTORE_PASSWORD")
            keyAlias = System.getenv("KEY_ALIAS")
            keyPassword = System.getenv("KEY_PASSWORD")
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            isDebuggable = true
        }
    }
    lintOptions {
        // Turns off checks for the issue IDs you specify.
        disable("TypographyFractions", "TypographyQuotes")
        // Turns on checks for the issue IDs you specify. These checks are in
        // addition to the default lint checks.
        enable("RtlHardcoded", "RtlCompat", "RtlEnabled")
        // To enable checks for only a subset of issue IDs and ignore all others,
        // list the issue IDs with the 'check' property instead. This property overrides
        // any issue IDs you enable or disable using the properties above.
        check("NewApi", "InlinedApi")
        // If set to true, turns off analysis progress reporting by lint.
        isQuiet = true
        // if set to true (default), stops the build if errors are found.
        isAbortOnError = false
        // if true, only report errors.
        isIgnoreWarnings = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}")
    implementation("com.android.support:appcompat-v7:${Versions.appCompat}")
    implementation("com.android.support:support-media-compat:${Versions.appCompat}")
    implementation("com.android.support:support-v4:${Versions.appCompat}")
    implementation("com.android.support.constraint:constraint-layout:${Versions.constraintLayout}")
    testImplementation("junit:junit:${Versions.junit}")
    androidTestImplementation("com.android.support.test:runner:${Versions.runner}")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:${Versions.espressoCore}")
    implementation("com.google.firebase:firebase-core:${Versions.firebaseCore}")
    implementation("com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}") {
        isTransitive = true;
    }
}

apply(plugin = "com.google.gms.google-services")